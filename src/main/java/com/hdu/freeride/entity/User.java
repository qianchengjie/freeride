package com.hdu.freeride.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.purse, purse) != 0) return false;
        if (status != null ? !status.equals(user.age) : user.status != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (nikeName != null ? !nikeName.equals(user.nikeName) : user.nikeName != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (personSign != null ? !personSign.equals(user.personSign) : user.personSign != null) return false;
        if (industry != null ? !industry.equals(user.industry) : user.industry != null) return false;
        if (date != null ? !date.equals(user.date) : user.date != null) return false;
        if (msg != null ? !msg.equals(user.msg) : user.msg != null) return false;
        if (headImg != null ? !headImg.equals(user.headImg) : user.headImg != null) return false;

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
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }

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
                '}';
    }
}
