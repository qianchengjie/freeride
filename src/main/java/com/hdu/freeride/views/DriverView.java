package com.hdu.freeride.views;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/25 20:54
 */
public class DriverView {

    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private String name;
    private String date;
    @NotNull
    private String driverLicense;
    @NotNull
    private String drivingLicense;
    private Integer status;
    private Integer allCount;
    private Integer successCount;
    private Integer failCount;
    private double income;
    private Integer nowCar;

    private Integer carId;
    @NotNull
    private String vehicleBrand;
    @NotNull
    private String vehicleColor;
    @NotNull
    private String vehicleCode;

    public DriverView() {}
    public DriverView(Integer id, Integer userId, String name, String date, String driverLicense, String drivingLicense, Integer status, Integer allCount, Integer successCount, Integer failCount, double income, Integer nowCar, Integer carId, String vehicleBrand, String vehicleColor, String vehicleCode) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.driverLicense = driverLicense;
        this.drivingLicense = drivingLicense;
        this.status = status;
        this.allCount = allCount;
        this.successCount = successCount;
        this.failCount = failCount;
        this.income = income;
        this.nowCar = nowCar;
        this.carId = carId;
        this.vehicleBrand = vehicleBrand;
        this.vehicleColor = vehicleColor;
        this.vehicleCode = vehicleCode;
    }

    @Override
    public String toString() {
        return "DriverView{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", drivingLicense='" + drivingLicense + '\'' +
                ", status=" + status +
                ", allCount=" + allCount +
                ", successCount=" + successCount +
                ", failCount=" + failCount +
                ", income=" + income +
                ", nowCar=" + nowCar +
                ", carId=" + carId +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                ", vehicleCode='" + vehicleCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverView that = (DriverView) o;
        return Double.compare(that.income, income) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(driverLicense, that.driverLicense) &&
                Objects.equals(drivingLicense, that.drivingLicense) &&
                Objects.equals(status, that.status) &&
                Objects.equals(allCount, that.allCount) &&
                Objects.equals(successCount, that.successCount) &&
                Objects.equals(failCount, that.failCount) &&
                Objects.equals(nowCar, that.nowCar) &&
                Objects.equals(carId, that.carId) &&
                Objects.equals(vehicleBrand, that.vehicleBrand) &&
                Objects.equals(vehicleColor, that.vehicleColor) &&
                Objects.equals(vehicleCode, that.vehicleCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, name, date, driverLicense, drivingLicense, status, allCount, successCount, failCount, income, nowCar, carId, vehicleBrand, vehicleColor, vehicleCode);
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Integer getNowCar() {
        return nowCar;
    }

    public void setNowCar(Integer nowCar) {
        this.nowCar = nowCar;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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
