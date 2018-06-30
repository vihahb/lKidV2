package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ClassCurrentTeacher extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("class_code")
    @Expose
    private String classCode;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("school_year_id")
    @Expose
    private Integer schoolYearId;
    @SerializedName("grade_id")
    @Expose
    private Integer gradeId;
    @SerializedName("avatar_thumb")
    @Expose
    private String avatarThumb;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(Integer schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getAvatarThumb() {
        return avatarThumb;
    }

    public void setAvatarThumb(String avatarThumb) {
        this.avatarThumb = avatarThumb;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ClassCurrentTeacher{" +
                "id=" + id +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", schoolYearId=" + schoolYearId +
                ", gradeId=" + gradeId +
                ", avatarThumb='" + avatarThumb + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}