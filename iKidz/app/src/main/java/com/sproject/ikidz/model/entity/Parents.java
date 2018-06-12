package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/8/2018
 * Time: 11:29 PM
 * Email: vihahb@gmail.com
 */
public class Parents extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("birthday")
    @Expose
    private String birthday;

    /**
     * No args constructor for use in serialization
     */
    public Parents() {
    }

    /**
     * @param id
     * @param birthday
     * @param phone
     * @param address
     * @param email
     * @param userId
     * @param job
     * @param gender
     * @param fullName
     */
    public Parents(Integer id, String fullName, Integer gender, String phone, String email, Integer userId, String job, String address, String birthday) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
        this.job = job;
        this.address = address;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                ", job='" + job + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
