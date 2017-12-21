package com.hdu.freeride.entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_desc")
    private String roleDesc;
    private String date;
    @Column(name = "p_id")
    private int pId;

    //1超级管理员，2管理员，3司机，4乘客
    public static final int SUPERADMIN = 1;
    public static final int ADMIN = 2;
    public static final int DRIVER = 3;
    public static final int PASSENGER = 4;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

        Role role = (Role) o;

        if (id != role.id) return false;
        if (pId != role.pId) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;
        if (roleDesc != null ? !roleDesc.equals(role.roleDesc) : role.roleDesc != null) return false;
        if (date != null ? !date.equals(role.date) : role.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + pId;
        return result;
    }
}
