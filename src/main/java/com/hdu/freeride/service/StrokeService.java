package com.hdu.freeride.service;

import com.hdu.freeride.entity.*;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.handle.ExceptionHandle;
import com.hdu.freeride.repository.*;
import com.hdu.freeride.views.StrokeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 9:53
 */
@Service
public class StrokeService {

    public final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    private final static int ONE_PAGE_SUM = 10;
    private final static Sort SORT_ID_DESC = new Sort(Sort.Direction.DESC, "id");

    @Autowired
    private StrokeRepository strokeRepository;

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private UserRoleStrokeRelationRepository userRoleStrokeRelationRepository;

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    /**
     * 用户发布行程（包括司机和乘客）
     * @param strokeView
     * @return
     */
    @Transactional
    public StrokeView add(StrokeView strokeView) {
        Stroke stroke = new Stroke();
        stroke.setStart(strokeView.getStart());
        stroke.setEnd(strokeView.getEnd());
        stroke.setAmount(strokeView.getAmount());
        stroke.setPrice(strokeView.getPrice());
        stroke.setBeginTime(strokeView.getBeginTime());
        stroke.setStatus(Stroke.STATUS_WAIT);
        stroke.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        stroke = strokeRepository.save(stroke);

        UserRoleStrokeRelation userRoleStrokeRelation = new UserRoleStrokeRelation();
        userRoleStrokeRelation.setStrokeId(stroke.getId());
        userRoleStrokeRelation.setUserId(strokeView.getUserId());
        userRoleStrokeRelation.setRoleId(strokeView.getRoleId());
        userRoleStrokeRelation = userRoleStrokeRelationRepository.save(userRoleStrokeRelation);

        strokeView.setStrokeId(stroke.getId());
        strokeView.setStatus(stroke.getStatus());
        strokeView.setCreateTime(stroke.getCreateTime());
        strokeView.setId(userRoleStrokeRelation.getId());
        return strokeView;
    }

    /**
     * 用户取消行程
     * @param userId
     * @param roleId
     * @param strokeId
     */
    @Transactional
    public void cancel(Integer userId, Integer roleId, Integer strokeId) {
        Stroke stroke = strokeRepository.findOne(strokeId);
        Integer status = stroke.getStatus();
        if (status.equals(Stroke.STATUS_CANCEL) || status.equals(Stroke.STATUS_FINISH) || status.equals(Stroke.STATUS_ONWAY)) {
            throw new MyException("订单无法取消");
        }
        logger.info("roleId={}", roleId);
        if (roleId == Role.PASSENGER) {
            stroke.setStatus(Stroke.STATUS_CANCEL);
        } else if (roleId == Role.DRIVER) {
            stroke.setStatus(Stroke.STATUS_WAIT);
            userRoleStrokeRelationRepository.delete(userRoleStrokeRelationRepository.findByUserIdAndRoleIdAndStrokeId(userId, roleId, strokeId));
        }
        strokeRepository.save(stroke);
    }

    /**
     * 用户删除行程
     * @param userId
     * @param roleId
     * @param strokeId
     */
    public void delete(Integer userId, Integer roleId, Integer strokeId) {
        userRoleStrokeRelationRepository.delete(userRoleStrokeRelationRepository.findByUserIdAndRoleIdAndStrokeId(userId, roleId, strokeId));
    }

    /**
     * 通过行程id查找行程信息
     * @param id
     * @return
     */
    public Stroke findOne(int id) {
        return strokeRepository.findOne(id);
    }

    /**
     * 分页获取用户的某个角色的行程表
     * @param userId
     * @param roleId
     * @param pageNum
     * @return
     */
    @Transactional
    public Page<Stroke> findAllByUserIdAndRoleIdAndStatus(Integer userId, Integer roleId, Integer status, Integer pageNum) {
        Pageable pageable = new PageRequest(pageNum-1, ONE_PAGE_SUM, SORT_ID_DESC);
        if (pageNum == null ) {
            pageable = new PageRequest(pageNum-1, 999999, SORT_ID_DESC);
        }
        List<Integer> strokeIds = new ArrayList<Integer>();
        if (userId == null && roleId == null) {
            return strokeRepository.findAll(pageable);
        } else if (roleId == null) {
            strokeIds = userRoleStrokeRelationRepository.findAllStrokeIdByUserId(userId);
        } else if (userId == null) {
            strokeIds = userRoleStrokeRelationRepository.findAllStrokeIdByRoleId(roleId);
        } else {
            strokeIds = userRoleStrokeRelationRepository.findAllStrokeIdByUserIdAndRoleId(userId, roleId);
        }
        List<Integer> finalStrokeIds = strokeIds;
        if (finalStrokeIds.size() == 0) {
            pageable = new PageRequest(9999999, ONE_PAGE_SUM, SORT_ID_DESC);
            return strokeRepository.findAll(pageable);
        } else {
            Specification<Stroke> specification = new Specification<Stroke>() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list=new ArrayList<Predicate>();
                    if (status != null) {
                        list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
                    }
                    list.add(criteriaBuilder.in(root.get("id")).value(finalStrokeIds));
                    criteriaQuery.where(criteriaBuilder.and(list.toArray(new Predicate[list.size()])));
                    criteriaQuery.distinct(true);
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id").as(Integer.class)));
                    return criteriaQuery.getRestriction();
                }
            };
            return strokeRepository.findAll(specification, pageable);
        }
    }

    /**
     * 司机接单
     * @param strokeId
     * @param userId
     * @return
     */
    @Transactional
    public Stroke strokeTaking(Integer strokeId, Integer userId) {
        if (!permissionsService.checkUserRole(userId, Role.DRIVER)) {
            throw new MyException("您还不是司机");
        }
        Stroke stroke = strokeRepository.findOne(strokeId);
        stroke.setStatus(Stroke.STATUS_TAKING);
        stroke = strokeRepository.save(stroke);
        UserRoleStrokeRelation userRoleStrokeRelation = new UserRoleStrokeRelation();
        userRoleStrokeRelation.setStrokeId(stroke.getId());
        userRoleStrokeRelation.setUserId(userId);
        userRoleStrokeRelation.setRoleId(Role.DRIVER);
        userRoleStrokeRelationRepository.save(userRoleStrokeRelation);
        return stroke;
    }

    /**
     * 行程在路上
     * @param strokeId
     */
    public void startStroke(Integer strokeId) {
        Stroke stroke = strokeRepository.findById(strokeId);
        stroke.setStatus(Stroke.STATUS_ONWAY);
        strokeRepository.save(stroke);
    }

    /**
     * 行程结束并结算
     * @param strokeId
     */
    @Transactional
    public void finishStroke(Integer strokeId) {
        Stroke stroke = strokeRepository.findById(strokeId);
        List<UserRoleStrokeRelation> list = userRoleStrokeRelationRepository.findAllByStrokeId(strokeId);
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        for (UserRoleStrokeRelation item:list) {
            TransactionDetails transactionDetails = new TransactionDetails();
            transactionDetails.setDate(nowTime);
            transactionDetails.setSum(stroke.getPrice());
            transactionDetails.setStrokeId(strokeId);
            transactionDetails.setUserId(item.getUserId());
            User user = userRepository.findOne(item.getUserId());
            if (item.getRoleId() == Role.DRIVER) {
                transactionDetails.setType(TransactionDetails.INCOME);
                transactionDetails.setDescText("完成行程获得" + transactionDetails.getSum() + "元");

                Driver driver = driverRepository.findByUserId(item.getUserId());
                driver.setAllCount(driver.getAllCount() + 1);
                driver.setSuccessCount(driver.getSuccessCount() + 1);
                driver.setIncome(driver.getIncome() + stroke.getPrice());
                driverRepository.save(driver);

                user.setPurse(user.getPurse() + stroke.getPrice());
                userRepository.save(user);
            } else if (item.getRoleId() == Role.PASSENGER) {
                transactionDetails.setType(TransactionDetails.EXPENDITURE);
                transactionDetails.setDescText("打车支出" + transactionDetails.getSum() + "元");
                user.setPurse(user.getPurse() - stroke.getPrice());
                userRepository.save(user);
            }
            transactionDetailsRepository.save(transactionDetails);
        }
        stroke.setStatus(Stroke.STATUS_FINISH);
        stroke.setFinishTime(nowTime);
        strokeRepository.save(stroke);
    }

}
