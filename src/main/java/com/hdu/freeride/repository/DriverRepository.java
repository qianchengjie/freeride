package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 15:59
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findByUserId(Integer userId);

}
