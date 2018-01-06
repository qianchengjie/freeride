package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction_details")
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double sum;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "stroke_id")
    private Integer strokeId;

    @Column(name = "desc_text")
    private String descText;

    private String date;
    //0支出，1收入，2转入，3提现
    private Integer type;

    public final static Integer EXPENDITURE = 0;
    public final static Integer INCOME = 1;
    public final static Integer CHANGE_INTO = 2;
    public final static Integer WITHDRAWALS = 3;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getStrokeId() {
        return strokeId;
    }

    public void setStrokeId(Integer strokeId) {
        this.strokeId = strokeId;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDetails that = (TransactionDetails) o;

        if (id != that.id) return false;
        if (Double.compare(that.sum, sum) != 0) return false;
        if (userId != that.userId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (strokeId != null ? !strokeId.equals(that.strokeId) : that.strokeId != null) return false;
        if (descText != null ? !descText.equals(that.descText) : that.descText != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + userId;
        result = 31 * result + (strokeId != null ? strokeId.hashCode() : 0);
        result = 31 * result + (descText != null ? descText.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "id=" + id +
                ", sum=" + sum +
                ", userId=" + userId +
                ", strokeId=" + strokeId +
                ", descText='" + descText + '\'' +
                ", date='" + date + '\'' +
                ", type=" + type +
                '}';
    }
}
