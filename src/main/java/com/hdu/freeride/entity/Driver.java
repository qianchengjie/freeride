package com.hdu.freeride.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 23:36
 */
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    @Pattern(regexp = "^.{2,8}$", message = "姓名长度2-8位")
    private String name;
    private String date;
    @NotNull
    @Column(name = "driver_license")
    private String driverLicense;
    @NotNull
    @Column(name = "driving_license")
    private String drivingLicense;
    private Integer status;
    @Column(name = "all_count")
    private Integer allCount;
    @Column(name = "success_count")
    private Integer successCount;
    @Column(name = "fail_count")
    private Integer failCount;
    private double income;
    @Column(name = "now_car")
    private Integer nowCar;

    //0未出车，1出车，默认2待审核
    public static final Integer STATUS_STOP = 0;
    public static final Integer STATUS_START = 1;
    public static final Integer STATUS_REVIEW = 2;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Double.compare(driver.income, income) == 0 &&
                Objects.equals(id, driver.id) &&
                Objects.equals(userId, driver.userId) &&
                Objects.equals(name, driver.name) &&
                Objects.equals(date, driver.date) &&
                Objects.equals(driverLicense, driver.driverLicense) &&
                Objects.equals(drivingLicense, driver.drivingLicense) &&
                Objects.equals(status, driver.status) &&
                Objects.equals(allCount, driver.allCount) &&
                Objects.equals(successCount, driver.successCount) &&
                Objects.equals(failCount, driver.failCount) &&
                Objects.equals(nowCar, driver.nowCar);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, name, date, driverLicense, drivingLicense, status, allCount, successCount, failCount, income, nowCar);
    }

    @Override
    public String toString() {
        return "Driver{" +
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
                ", now_car=" + nowCar +
                '}';
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
}
