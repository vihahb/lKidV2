package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterEntity {

    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("avatar_student")
    @Expose
    private String avatarStudent;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("id")
    @Expose
    private String id;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarStudent() {
        return avatarStudent;
    }

    public void setAvatarStudent(String avatarStudent) {
        this.avatarStudent = avatarStudent;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}