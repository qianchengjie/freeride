package com.hdu.freeride.service;

import com.hdu.freeride.aspect.HttpAspect;
import com.hdu.freeride.entity.Car;
import com.hdu.freeride.entity.Driver;
import com.hdu.freeride.entity.Role;
import com.hdu.freeride.entity.UserRoleRelation;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.repository.CarRepository;
import com.hdu.freeride.repository.DriverRepository;
import com.hdu.freeride.repository.UserRoleRelationRepository;
import com.hdu.freeride.util.FileUtil;
import com.hdu.freeride.views.DriverView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 16:00
 */
@Service
public class DriverService {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    private static final String DRIVER_LICENSE_PATH = "images" + File.separator + "driverlicense" + File.separator;
    private static final String DRIVING_LICENSE_PATH = "images" + File.separator + "drivinglicense" + File.separator;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRoleRelationRepository userRoleRelationRepository;

    @Autowired
    private PermissionsService permissionsService;

    /**
     * 获得司机信息
     * @param userId
     * @return
     */
    public DriverView find(Integer userId) {
        if (!isDriver(userId)) {
            throw new MyException("还没申请成为司机");
        }
        Driver driver = driverRepository.findByUserId(userId);
        Car car = carRepository.findOne(driver.getNowCar());
        return new DriverView(driver.getId(), driver.getUserId(), driver.getName(),
                driver.getDate(), driver.getDriverLicense(), driver.getDrivingLicense(),
                driver.getStatus(), driver.getAllCount(), driver.getSuccessCount(),
                driver.getFailCount(), driver.getIncome(), driver.getNowCar(),
                car.getId(), car.getVehicleBrand(), car.getVehicleColor(),
                car.getVehicleCode());

    }

    /**
     * 判断是否是司机
     * @param userId
     * @return
     */
    public Boolean isDriver(Integer userId) {
        return permissionsService.checkUserRole(userId, Role.DRIVER);
    }

    /**
     * 申请成为车主
     * @param driverView
     * @return
     */
    @Transactional
    public DriverView applyDriver(DriverView driverView) {
        if (isDriver(driverView.getUserId())) {
            throw new MyException("已经申请为司机");
        }
        if (carRepository.findByVehicleCode(driverView.getVehicleCode()) != null) {
            throw new MyException("该车牌已被注册");
        }

        Driver driver = new Driver();
        driver.setUserId(driverView.getUserId());
        driver.setName(driverView.getName());
        driver.setDriverLicense(driverView.getDriverLicense());
        driver.setDrivingLicense(driverView.getDrivingLicense());
        driver.setDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//        driver.setStatus(Driver.STATUS_REVIEW);
        driver.setStatus(Driver.STATUS_STOP);
        driver.setSuccessCount(0);
        driver.setAllCount(0);
        driver.setFailCount(0);
        driver = driverRepository.save(driver);

        Car car = new Car();
        car.setDriverId(driver.getId());
        car.setVehicleBrand(driverView.getVehicleBrand());
        car.setVehicleCode(driverView.getVehicleCode());
        car.setVehicleColor(driverView.getVehicleColor());
        car = carRepository.save(car);

        driver.setNowCar(car.getId());
        driver = driverRepository.save(driver);

        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(driverView.getUserId());
        userRoleRelation.setRoleId(Role.DRIVER);
        userRoleRelationRepository.save(userRoleRelation);

        driverView.setId(driver.getId());
        driverView.setCarId(car.getId());
        driverView.setStatus(driver.getStatus());
        driverView.setDate(driver.getDate());

        return driverView;
    }

    /**
     * 上传驾照图片
     * @param file
     * @param req
     */
    public String uploadDriverLicense(MultipartFile file, HttpServletRequest req) {
        String realPath = req.getServletContext().getRealPath("");
        realPath = realPath.substring(0, realPath.length()-1);
        String filePath = realPath.substring(0, realPath.lastIndexOf(File.separator) + 1) + DRIVER_LICENSE_PATH;
        return FileUtil.upload(file, filePath);
    }

    /**
     * 上传行驶证图片
     * @param file
     * @param req
     * @return
     */
    public String uploadDrivingLicense(MultipartFile file, HttpServletRequest req) {
        String realPath = req.getServletContext().getRealPath("");
        realPath = realPath.substring(0, realPath.length()-1);
        String filePath = realPath.substring(0, realPath.lastIndexOf(File.separator) + 1) + DRIVING_LICENSE_PATH;
        return FileUtil.upload(file, filePath);
    }

    /**
     * 开始出车
     * @param userId
     */
    public void startWork(Integer userId) {
        if (!isDriver(userId)) {
            throw new MyException("还没申请成为司机");
        }
        Driver driver = driverRepository.findByUserId(userId);
        if (driver.getStatus().equals(Driver.STATUS_REVIEW)) {
            throw new MyException("等待审核");
        }
        driver.setStatus(Driver.STATUS_START);
        driverRepository.save(driver);
    }

    /**
     * 停止出车
     * @param userId
     */
    public void stopWork(Integer userId) {
        if (!isDriver(userId)) {
            throw new MyException("还没申请成为司机");
        }
        Driver driver = driverRepository.findByUserId(userId);
        if (driver.getStatus().equals(Driver.STATUS_REVIEW)) {
            throw new MyException("等待审核");
        }
        driver.setStatus(Driver.STATUS_STOP);
        driverRepository.save(driver);
    }

}
