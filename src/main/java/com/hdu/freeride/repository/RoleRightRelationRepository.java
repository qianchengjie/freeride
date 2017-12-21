package com.hdu.freeride.repository;

import com.hdu.freeride.entity.RoleRightRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 11:04
 */
@Repository
public interface RoleRightRelationRepository extends JpaRepository<RoleRightRelation, Integer> {

    public RoleRightRelation findByRoleIdAndRightId(int roleId, int rightId);

}
