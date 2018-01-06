package com.hdu.freeride.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stroke")
public class Stroke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    @Column(name = "begin_time")
    private String beginTime;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "finish_time")
    private String finishTime;

    //0等待接单,1已接单，2行程中,3已结单，4订单已取消
    public static final Integer STATUS_WAIT = 0;
    public static final Integer STATUS_TAKING = 1;
    public static final Integer STATUS_ONWAY = 2;
    public static final Integer STATUS_FINISH = 3;
    public static final Integer STATUS_CANCEL = 4;


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

    public Integer getAmount(){
        return this.amount;
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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stroke stroke = (Stroke) o;

        if (id != stroke.id) return false;
        if (Double.compare(stroke.price, price) != 0) return false;
        if (status != null ? !status.equals(stroke.status) : stroke.status != null) return false;
        if (amount != null ? !amount.equals(stroke.amount) : stroke.amount != null) return false;
        if (start != null ? !start.equals(stroke.start) : stroke.start != null) return false;
        if (end != null ? !end.equals(stroke.end) : stroke.end != null) return false;
        if (beginTime != null ? !beginTime.equals(stroke.beginTime) : stroke.beginTime != null) return false;
        if (finishTime != null ? !finishTime.equals(stroke.finishTime) : stroke.finishTime != null) return false;
        if (createTime != null ? !createTime.equals(stroke.createTime) : stroke.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stroke{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", amount=" + amount +
                ", beginTime='" + beginTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
