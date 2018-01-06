package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
@Table(name = "right")
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "right_name")
    private String rightName;
    @Column(name = "right_desc")
    private String rightDesc;
    private String date;
    @Column(name = "p_id")
    private int pId;


    private static final int VIEW_RIGHT = 1;
    private static final int MODIFY_RIGHT = 2;
    private static final int APP_ALL_RIGHT = 3;
    private static final int CALL_RIGHT = 4;
    private static final int TAKE_RIGHT = 5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
