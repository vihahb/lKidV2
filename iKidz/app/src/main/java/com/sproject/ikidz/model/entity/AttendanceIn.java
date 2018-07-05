package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceIn {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("at_id")
    @Expose
    private String atId;
    @SerializedName("absent_id")
    @Expose
    private String absentId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("received")
    @Expose
    private String received;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("is_absent")
    @Expose
    private String isAbsent;
    @SerializedName("absent_parent_full_name")
    @Expose
    private String absentParentFullName;
    @SerializedName("absent_start")
    @Expose
    private String absentStart;
    @SerializedName("absent_end")
    @Expose
    private String absentEnd;
    @SerializedName("checked")
    @Expose
    private Integer checked;

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

    public String getAbsentId() {
        return absentId;
    }

    public void setAbsentId(String absentId) {
        this.absentId = absentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIsAbsent() {
        return isAbsent;
    }

    public void setIsAbsent(String isAbsent) {
        this.isAbsent = isAbsent;
    }

    public String getAbsentParentFullName() {
        return absentParentFullName;
    }

    public void setAbsentParentFullName(String absentParentFullName) {
        this.absentParentFullName = absentParentFullName;
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

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}