package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrugEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("received")
    @Expose
    private Integer received;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("full_name_student")
    @Expose
    private String fullNameStudent;
    @SerializedName("avatar_student")
    @Expose
    private String avatarStudent;
    @SerializedName("full_name_sender")
    @Expose
    private String fullNameSender;
    @SerializedName("avatar_sender")
    @Expose
    private String avatarSender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFullNameStudent() {
        return fullNameStudent;
    }

    public void setFullNameStudent(String fullNameStudent) {
        this.fullNameStudent = fullNameStudent;
    }

    public String getAvatarStudent() {
        return avatarStudent;
    }

    public void setAvatarStudent(String avatarStudent) {
        this.avatarStudent = avatarStudent;
    }

    public String getFullNameSender() {
        return fullNameSender;
    }

    public void setFullNameSender(String fullNameSender) {
        this.fullNameSender = fullNameSender;
    }

    public String getAvatarSender() {
        return avatarSender;
    }

    public void setAvatarSender(String avatarSender) {
        this.avatarSender = avatarSender;
    }

}