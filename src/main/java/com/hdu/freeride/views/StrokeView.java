package com.hdu.freeride.views;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 18:57
 */
public class StrokeView {

    private int id;
    @NotNull
    private String start;
    @NotNull
    private String end;
    @NotNull
    private double price;
    private Integer status;
    @NotNull
    private Integer amount;
    @NotNull
    private String beginTime;
    private String createTime;
    private String finishTime;

    @NotNull
    private int userId;
    @NotNull
    private int roleId;
    private int strokeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStrokeId() {
        return strokeId;
    }

    public void setStrokeId(int strokeId) {
        this.strokeId = strokeId;
    }

    @Override
    public String toString() {
        return "StrokeView{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", amount=" + amount +
                ", beginTime='" + beginTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", strokeId=" + strokeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StrokeView that = (StrokeView) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                userId == that.userId &&
                roleId == that.roleId &&
                strokeId == that.strokeId &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(status, that.status) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(finishTime, that.finishTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, start, end, price, status, amount, beginTime, createTime, finishTime, userId, roleId, strokeId);
    }
}
