package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LearnActivityEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("learn_morning")
    @Expose
    private String learnMorning;
    @SerializedName("learn_afternoon")
    @Expose
    private String learnAfternoon;
    @SerializedName("activity_date")
    @Expose
    private String activityDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLearnMorning() {
        return learnMorning;
    }

    public void setLearnMorning(String learnMorning) {
        this.learnMorning = learnMorning;
    }

    public String getLearnAfternoon() {
        return learnAfternoon;
    }

    public void setLearnAfternoon(String learnAfternoon) {
        this.learnAfternoon = learnAfternoon;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

}