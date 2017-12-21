package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Right;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/18 11:03
 */
@Repository
public interface RightRepository extends JpaRepository<Right, Integer> {
}
