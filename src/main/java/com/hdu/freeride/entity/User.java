package com.hdu.freeride.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class User {
    private int id;
    @NotNull(message = "用户名不能为空")
    @Length(max = 8, message = "用户名不能超过8位")
    private String name;
    @NotNull(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度8-16位")
    @Pattern(regexp = "([a-z]|[A-Z]|[0-9])+", message = "密码只能由数字和英文组成")
    private String password;
    private Integer age;
    private String nikeName;
    private String phone;
    private String personSign;
    private String industry;
    private String date;
    private double purse;
    private byte status;
    private String msg;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "nike_name")
    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "person_sign")
    public String getPersonSign() {
        return personSign;
    }

    public void setPersonSign(String personSign) {
        this.personSign = personSign;
    }

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
    @Column(name = "purse")
    public double getPurse() {
        return purse;
    }

    public void setPurse(double purse) {
        this.purse = purse;
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
    @Column(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.purse, purse) != 0) return false;
        if (status != user.status) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (nikeName != null ? !nikeName.equals(user.nikeName) : user.nikeName != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (personSign != null ? !personSign.equals(user.personSign) : user.personSign != null) return false;
        if (industry != null ? !industry.equals(user.industry) : user.industry != null) return false;
        if (date != null ? !date.equals(user.date) : user.date != null) return false;
        if (msg != null ? !msg.equals(user.msg) : user.msg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (nikeName != null ? nikeName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (personSign != null ? personSign.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(purse);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) status;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }
}
