package com.hdu.freeride.service;

import com.hdu.freeride.entity.Role;
import com.hdu.freeride.entity.Stroke;
import com.hdu.freeride.entity.UserRoleStrokeRelation;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.handle.ExceptionHandle;
import com.hdu.freeride.repository.StrokeRepository;
import com.hdu.freeride.repository.UserRoleStrokeRelationRepository;
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
    public Page<Stroke> findAllByUserIdAndRoleId(Integer userId, Integer roleId, int pageNum) {
        Pageable pageable = new PageRequest(pageNum-1, ONE_PAGE_SUM, SORT_ID_DESC);
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
        Specification<Stroke> specification = new Specification<Stroke>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.in(root.get("id")).value(finalStrokeIds);
            }
        };
        return strokeRepository.findAll(specification, pageable);
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

}
