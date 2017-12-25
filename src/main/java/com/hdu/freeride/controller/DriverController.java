package com.hdu.freeride.controller;

import com.hdu.freeride.entity.Car;
import com.hdu.freeride.entity.Driver;
import com.hdu.freeride.service.DriverService;
import com.hdu.freeride.util.ResultUtil;
import com.hdu.freeride.views.DriverView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 16:04
 */
@RestController
@RequestMapping("driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * 申请成为车主
     * @param driverView
     * @param bindingResult
     * @return
     */
    @PostMapping("applyDriver")
    public Object applyDriver(@Valid DriverView driverView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult);
        }
        return ResultUtil.success(driverService.applyDriver(driverView));
    }

    /**
     * 上传驾照图片
     * @param file
     * @param req
     * @return
     */
    @PostMapping("uploadDriverLicense")
    public Object uploadDriverLicense(MultipartFile file, HttpServletRequest req) {
        return ResultUtil.success(driverService.uploadDriverLicense(file, req));
    }

    /**
     * 上传行驶证图片
     * @param file
     * @param req
     * @return
     */
    @PostMapping("uploadDrivingLicense")
    public Object uploadDrivingLicense(MultipartFile file, HttpServletRequest req) {
        return ResultUtil.success(driverService.uploadDrivingLicense(file, req));
    }

    /**
     * 获得司机信息
     * @param userId
     * @return
     */
    @GetMapping("find/{userId}")
    public Object find(@PathVariable Integer userId) {
        if (userId == null) {
            return ResultUtil.error("缺少参数");
        } else if (!driverService.isDriver(userId)) {
            return ResultUtil.success(1);
        }
        return ResultUtil.success(driverService.find(userId));
    }

    /**
     * 开始出车
     * @param userId
     * @return
     */
    @PostMapping("startWork")
    public Object startWork(Integer userId) {
        if (userId == null) {
            return ResultUtil.error("缺少参数");
        }
        driverService.startWork(userId);
        return ResultUtil.success();
    }

    /**
     * 停止出车
     * @param userId
     * @return
     */
    @PostMapping("stopWork")
    public Object stopWork(Integer userId) {
        if (userId == null) {
            return ResultUtil.error("缺少参数");
        }
        driverService.stopWork(userId);
        return ResultUtil.success();
    }

}
