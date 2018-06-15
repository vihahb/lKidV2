package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountNotify {

    @SerializedName("add-drug")
    @Expose
    private Integer addDrug;
    @SerializedName("leave-school")
    @Expose
    private Integer leaveSchool;
    @SerializedName("register-more-time")
    @Expose
    private Integer registerMoreTime;

    public Integer getAddDrug() {
        return addDrug;
    }

    public void setAddDrug(Integer addDrug) {
        this.addDrug = addDrug;
    }

    public Integer getLeaveSchool() {
        return leaveSchool;
    }

    public void setLeaveSchool(Integer leaveSchool) {
        this.leaveSchool = leaveSchool;
    }

    public Integer getRegisterMoreTime() {
        return registerMoreTime;
    }

    public void setRegisterMoreTime(Integer registerMoreTime) {
        this.registerMoreTime = registerMoreTime;
    }

    @Override
    public String toString() {
        return "CountNotify{" +
                "addDrug=" + addDrug +
                ", leaveSchool=" + leaveSchool +
                ", registerMoreTime=" + registerMoreTime +
                '}';
    }
}