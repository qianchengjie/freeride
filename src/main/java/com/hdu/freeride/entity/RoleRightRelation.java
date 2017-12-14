package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
@Table(name = "role_right_relation", schema = "freeride", catalog = "")
public class RoleRightRelation {
    private int id;
    private int roleId;
    private int rightId;

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
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "right_id")
    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRightRelation that = (RoleRightRelation) o;

        if (id != that.id) return false;
        if (roleId != that.roleId) return false;
        if (rightId != that.rightId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roleId;
        result = 31 * result + rightId;
        return result;
    }
}
