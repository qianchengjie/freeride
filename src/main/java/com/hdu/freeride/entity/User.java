package com.hdu.freeride.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^.{2,8}$", message = "用户名长度2-8位")
    private String name;
    @NotNull(message = "密码不能为空")
    private String password;
    private Integer age;
    @Column(name = "nike_name")
    private String nikeName;
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$", message = "请填写正确的手机号")
    private String phone;
    @Column(name = "person_sign")
    private String personSign;
    private String industry;
    private String date;
    private double purse;
    private Integer status;
    private String msg;
    @Column(name = "head_img")
    private String headImg;
    @Column(name = "last_online_time")
    private String lastOnlineTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", nikeName='" + nikeName + '\'' +
                ", phone='" + phone + '\'' +
                ", personSign='" + personSign + '\'' +
                ", industry='" + industry + '\'' +
                ", date='" + date + '\'' +
                ", purse=" + purse +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", headImg='" + headImg + '\'' +
                ", lastOnlineTime='" + lastOnlineTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Double.compare(user.purse, purse) == 0 &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(age, user.age) &&
                Objects.equals(nikeName, user.nikeName) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(personSign, user.personSign) &&
                Objects.equals(industry, user.industry) &&
                Objects.equals(date, user.date) &&
                Objects.equals(status, user.status) &&
                Objects.equals(msg, user.msg) &&
                Objects.equals(headImg, user.headImg) &&
                Objects.equals(lastOnlineTime, user.lastOnlineTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, age, nikeName, phone, personSign, industry, date, purse, status, msg, headImg, lastOnlineTime);
    }

    public String getLastOnlineTime() {

        return lastOnlineTime;
    }

    public void setLastOnlineTime(String lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonSign() {
        return personSign;
    }

    public void setPersonSign(String personSign) {
        this.personSign = personSign;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPurse() {
        return purse;
    }

    public void setPurse(double purse) {
        this.purse = purse;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

}
