package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teachers {

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
    @SerializedName("teacher_code")
    @Expose
    private String teacherCode;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    /**
     * No args constructor for use in serialization
     */
    public Teachers() {
    }

    /**
     * @param id
     * @param phone
     * @param teacherCode
     * @param email
     * @param userId
     * @param gender
     * @param fullName
     */
    public Teachers(Integer id, String fullName, Integer gender, String phone, String email, String teacherCode, Integer userId) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.teacherCode = teacherCode;
        this.userId = userId;
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

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", teacherCode='" + teacherCode + '\'' +
                ", userId=" + userId +
                '}';
    }
}