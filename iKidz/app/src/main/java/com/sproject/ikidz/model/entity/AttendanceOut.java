package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceOut {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("at_id")
    @Expose
    private String atId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("checked")
    @Expose
    private String checked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

}