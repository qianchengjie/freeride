package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
public class Stroke {
    private int id;
    private String start;
    private String end;
    private double price;
    private byte status;
    private byte amount;
    private String beginTime;
    private String finishTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start")
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "amount")
    public byte getAmount() {
        return amount;
    }

    public void setAmount(byte amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "begin_time")
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "finish_time")
    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stroke stroke = (Stroke) o;

        if (id != stroke.id) return false;
        if (Double.compare(stroke.price, price) != 0) return false;
        if (status != stroke.status) return false;
        if (amount != stroke.amount) return false;
        if (start != null ? !start.equals(stroke.start) : stroke.start != null) return false;
        if (end != null ? !end.equals(stroke.end) : stroke.end != null) return false;
        if (beginTime != null ? !beginTime.equals(stroke.beginTime) : stroke.beginTime != null) return false;
        if (finishTime != null ? !finishTime.equals(stroke.finishTime) : stroke.finishTime != null) return false;

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
        result = 31 * result + (int) status;
        result = 31 * result + (int) amount;
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        return result;
    }
}
