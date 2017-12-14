package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role_stroke_relation", schema = "freeride", catalog = "")
public class UserRoleStrokeRelation {
    private int id;
    private int userId;
    private int roleId;
    private int strokeId;

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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "stroke_id")
    public int getStrokeId() {
        return strokeId;
    }

    public void setStrokeId(int strokeId) {
        this.strokeId = strokeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleStrokeRelation that = (UserRoleStrokeRelation) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;
        if (strokeId != that.strokeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + roleId;
        result = 31 * result + strokeId;
        return result;
    }
}
