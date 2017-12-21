package com.hdu.freeride.repository;

import com.hdu.freeride.entity.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 11:05
 */
public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Integer> {

    public UserRoleRelation findByUserIdAndRoleId(int userId, int roleId);

}
