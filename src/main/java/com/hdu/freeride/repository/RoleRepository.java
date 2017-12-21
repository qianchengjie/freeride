package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 11:02
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
