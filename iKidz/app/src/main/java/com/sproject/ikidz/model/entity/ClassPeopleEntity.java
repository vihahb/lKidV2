package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassPeopleEntity {
    private boolean isDivider;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("relationship_name")
    @Expose
    private String relationshipName;
    @SerializedName("first_name_student")
    @Expose
    private String first_name_student;
    @SerializedName("teacher_code")
    @Expose
    private String teacherCode;

    public ClassPeopleEntity(boolean isDivider) {
        this.isDivider = isDivider;
    }

    public boolean isDivider() {
        return isDivider;
    }

    public void setDivider(boolean divider) {
        isDivider = divider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getFirst_name_student() {
        return first_name_student;
    }

    public void setFirst_name_student(String first_name_student) {
        this.first_name_student = first_name_student;
    }
}
