package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbsentEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("absent_start")
    @Expose
    private String absentStart;
    @SerializedName("absent_end")
    @Expose
    private String absentEnd;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("received")
    @Expose
    private Integer received;
    @SerializedName("full_name_student")
    @Expose
    private String fullNameStudent;
    @SerializedName("avatar_student")
    @Expose
    private String avatarStudent;
    @SerializedName("student_code")
    @Expose
    private String studentCode;
    @SerializedName("birthday_student")
    @Expose
    private String birthdayStudent;
    @SerializedName("full_name_parent")
    @Expose
    private String fullNameParent;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("avatar_parent")
    @Expose
    private String avatarParent;
    @SerializedName("relationship_name")
    @Expose
    private String relationshipName;
    @SerializedName("class_name")
    @Expose
    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbsentStart() {
        return absentStart;
    }

    public void setAbsentStart(String absentStart) {
        this.absentStart = absentStart;
    }

    public String getAbsentEnd() {
        return absentEnd;
    }

    public void setAbsentEnd(String absentEnd) {
        this.absentEnd = absentEnd;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getBirthdayStudent() {
        return birthdayStudent;
    }

    public void setBirthdayStudent(String birthdayStudent) {
        this.birthdayStudent = birthdayStudent;
    }

    public String getFullNameParent() {
        return fullNameParent;
    }

    public void setFullNameParent(String fullNameParent) {
        this.fullNameParent = fullNameParent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarParent() {
        return avatarParent;
    }

    public void setAvatarParent(String avatarParent) {
        this.avatarParent = avatarParent;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}