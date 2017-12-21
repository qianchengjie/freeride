package com.hdu.freeride.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role_stroke_relation")
public class UserRoleStrokeRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @NotNull
    @Column(name = "role_id")
    private int roleId;
    @NotNull
    @Column(name = "stroke_id")
    private int strokeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
