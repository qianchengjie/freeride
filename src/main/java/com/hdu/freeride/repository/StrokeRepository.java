package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Stroke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 9:53
 */
@Repository
public interface StrokeRepository extends JpaRepository<Stroke, Integer> {
    
}
