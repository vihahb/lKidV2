package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LearnOverTimeEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("start_day")
    @Expose
    private String startDay;
    @SerializedName("end_day")
    @Expose
    private String endDay;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("received")
    @Expose
    private String received;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("time_pick")
    @Expose
    private String timePick;
    @SerializedName("avatar_student")
    @Expose
    private String avatarStudent;
    @SerializedName("full_name_student")
    @Expose
    private String fullNameStudent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimePick() {
        return timePick;
    }

    public void setTimePick(String timePick) {
        this.timePick = timePick;
    }

    public String getAvatarStudent() {
        return avatarStudent;
    }

    public void setAvatarStudent(String avatarStudent) {
        this.avatarStudent = avatarStudent;
    }

    public String getFullNameStudent() {
        return fullNameStudent;
    }

    public void setFullNameStudent(String fullNameStudent) {
        this.fullNameStudent = fullNameStudent;
    }

}
