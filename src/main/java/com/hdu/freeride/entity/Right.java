package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
public class Right {
    private int id;
    private String rightName;
    private String rightDesc;
    private String date;
    private int pId;

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
    @Column(name = "right_name")
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Basic
    @Column(name = "right_desc")
    public String getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc;
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
    @Column(name = "p_id")
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Right right = (Right) o;

        if (id != right.id) return false;
        if (pId != right.pId) return false;
        if (rightName != null ? !rightName.equals(right.rightName) : right.rightName != null) return false;
        if (rightDesc != null ? !rightDesc.equals(right.rightDesc) : right.rightDesc != null) return false;
        if (date != null ? !date.equals(right.date) : right.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rightName != null ? rightName.hashCode() : 0);
        result = 31 * result + (rightDesc != null ? rightDesc.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + pId;
        return result;
    }
}
