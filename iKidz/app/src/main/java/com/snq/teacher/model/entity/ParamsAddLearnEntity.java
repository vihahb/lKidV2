package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParamsAddLearnEntity {

    @SerializedName("learn_morning")
    @Expose
    private String learningMorning;
    @SerializedName("learn_afternoon")
    @Expose
    private String learningAfternoon;
    @SerializedName("id")
    @Expose
    private Integer id;

    public ParamsAddLearnEntity(String learningMorning, String learningAfternoon, Integer id) {
        this.learningMorning = learningMorning;
        this.learningAfternoon = learningAfternoon;
        this.id = id;
    }

    public String getLearningMorning() {
        return learningMorning;
    }

    public void setLearningMorning(String learningMorning) {
        this.learningMorning = learningMorning;
    }

    public String getLearningAfternoon() {
        return learningAfternoon;
    }

    public void setLearningAfternoon(String learningAfternoon) {
        this.learningAfternoon = learningAfternoon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}