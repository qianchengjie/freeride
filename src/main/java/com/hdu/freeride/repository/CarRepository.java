package com.hdu.freeride.repository;

import com.hdu.freeride.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 20:51
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    public Car findByVehicleCode(String vehicleCode);

}
