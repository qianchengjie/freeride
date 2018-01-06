package com.hdu.freeride.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 20:46
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "driver_id")
    private Integer driverId;
    @NotNull
    @Column(name = "vehicle_brand")
    private String vehicleBrand;
    @NotNull
    @Column(name = "vehicle_color")
    private String vehicleColor;
    @NotNull
    @Column(name = "vehicle_code")
    private String vehicleCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(driverId, car.driverId) &&
                Objects.equals(vehicleBrand, car.vehicleBrand) &&
                Objects.equals(vehicleColor, car.vehicleColor) &&
                Objects.equals(vehicleCode, car.vehicleCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, driverId, vehicleBrand, vehicleColor, vehicleCode);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                ", vehicleCode='" + vehicleCode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }
}
