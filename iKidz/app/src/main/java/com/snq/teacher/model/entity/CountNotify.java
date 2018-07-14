package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountNotify {
    //news field
    @SerializedName("add-drug")
    @Expose
    private Integer addDrug;
    @SerializedName("leave-school")
    @Expose
    private Integer leaveSchool;
    @SerializedName("register-more-time")
    @Expose
    private Integer registerMoreTime;

    //Nha Truong field
    @SerializedName("news-new")
    @Expose
    private Integer newsNew;
    @SerializedName("pickup")
    @Expose
    private Integer pickup;
    @SerializedName("register-eat")
    @Expose
    private Integer registerEat;
    @SerializedName("extracurricular")
    @Expose
    private Integer extracurricular;
    @SerializedName("study-program")
    @Expose
    private Integer studyProgram;
    @SerializedName("poll")
    @Expose
    private Integer poll;
    @SerializedName("take-care")
    @Expose
    private Integer takeCare;

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

    public Integer getNewsNew() {
        return newsNew;
    }

    public void setNewsNew(Integer newsNew) {
        this.newsNew = newsNew;
    }

    public Integer getPickup() {
        return pickup;
    }

    public void setPickup(Integer pickup) {
        this.pickup = pickup;
    }

    public Integer getRegisterEat() {
        return registerEat;
    }

    public void setRegisterEat(Integer registerEat) {
        this.registerEat = registerEat;
    }

    public Integer getExtracurricular() {
        return extracurricular;
    }

    public void setExtracurricular(Integer extracurricular) {
        this.extracurricular = extracurricular;
    }

    public Integer getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(Integer studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Integer getPoll() {
        return poll;
    }

    public void setPoll(Integer poll) {
        this.poll = poll;
    }

    public Integer getTakeCare() {
        return takeCare;
    }

    public void setTakeCare(Integer takeCare) {
        this.takeCare = takeCare;
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