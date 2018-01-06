package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Stroke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 9:53
 */
@Repository
public interface StrokeRepository extends JpaRepository<Stroke, Integer>, JpaSpecificationExecutor<Stroke> {

    public List<Stroke> findAllByStatus(Integer status);

    public Stroke findById(Integer id);

}
