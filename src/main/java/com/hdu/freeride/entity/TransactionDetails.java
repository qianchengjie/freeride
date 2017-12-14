package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction_details", schema = "freeride", catalog = "")
public class TransactionDetails {
    private int id;
    private double sum;
    private int userId;
    private Integer strokeId;
    private String desc;
    private String date;
    private byte type;

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
    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "stroke_id")
    public Integer getStrokeId() {
        return strokeId;
    }

    public void setStrokeId(Integer strokeId) {
        this.strokeId = strokeId;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
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
        if (type != that.type) return false;
        if (strokeId != null ? !strokeId.equals(that.strokeId) : that.strokeId != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
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
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) type;
        return result;
    }
}
