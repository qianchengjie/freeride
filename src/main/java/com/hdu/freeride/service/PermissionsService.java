package com.hdu.freeride.service;

import com.hdu.freeride.entity.RoleRightRelation;
import com.hdu.freeride.entity.User;
import com.hdu.freeride.entity.UserRoleRelation;
import com.hdu.freeride.exception.MyException;
import com.hdu.freeride.repository.RightRepository;
import com.hdu.freeride.repository.RoleRepository;
import com.hdu.freeride.repository.RoleRightRelationRepository;
import com.hdu.freeride.repository.UserRoleRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 11:01
 */
@Service
public class PermissionsService {

    @Autowired
    private RightRepository rightRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRelationRepository userRoleRelationRepository;

    @Autowired
    private RoleRightRelationRepository roleRightRelationRepository;

    /**
     * 添加用户角色
     * @param userId
     * @param roleId
     */
    public void addUserRole(int userId, int roleId) {
        if (checkUserRole(userId, roleId)) {
            throw new MyException("用户已拥有此角色");
        } else {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userId);
            userRoleRelation.setRoleId(roleId);
            userRoleRelationRepository.save(userRoleRelation);
        }
    }

    /**
     * 撤销用户角色
     * @param userId
     * @param roleId
     */
    public void revocationUserRole(int userId, int roleId) {
        UserRoleRelation userRoleRelation = userRoleRelationRepository.findByUserIdAndRoleId(userId, roleId);
        if (userRoleRelation == null) {
            throw new MyException("用户未拥有此角色");
        } else {
            userRoleRelationRepository.delete(userRoleRelation);
        }
    }

    /**
     * 添加角色权限
     * @param roleId
     * @param rightId
     */
    public void addRoleRight(int roleId, int rightId) {
        if (checkRoleRight(roleId, rightId)) {
            throw new MyException("角色已拥有此权限");
        } else {
            RoleRightRelation roleRightRelation = new RoleRightRelation();
            roleRightRelation.setRoleId(roleId);
            roleRightRelation.setRightId(rightId);
            roleRightRelationRepository.save(roleRightRelation);
        }
    }

    /**
     * 撤销角色权限
     * @param roleId
     * @param rightId
     */
    public void revocationRoleRight(int roleId, int rightId) {
        RoleRightRelation roleRightRelation = roleRightRelationRepository.findByRoleIdAndRightId(roleId, rightId);
        if (roleRightRelation == null) {
            throw new MyException("角色未拥有此权限");
        } else {
            roleRightRelationRepository.delete(roleRightRelation);
        }
    }



    /**
     * 检查用户是否拥有某个角色
     * @param userId
     * @param roleId
     * @return
     */
    public boolean checkUserRole(int userId, int roleId) {
        if (userRoleRelationRepository.findByUserIdAndRoleId(userId, roleId) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查角色是否拥有某个权限
     * @param roleId
     * @param rightId
     * @return
     */
    public boolean checkRoleRight(int roleId, int rightId) {
        if (roleRightRelationRepository.findByRoleIdAndRightId(roleId, rightId) != null) {
            return true;
        } else {
            return false;
        }
    }
}
