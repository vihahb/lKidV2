package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RespondResultCampaign {
    @Expose
    private String name;
    @Expose
    private String question;
    @Expose
    private int total;
    @Expose
    private List<ValueEntity> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<ValueEntity> getData() {
        return data;
    }

    public void setData(List<ValueEntity> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RespondResultCampaign{" +
                "name='" + name + '\'' +
                ", question='" + question + '\'' +
                ", data=" + data +
                '}';
    }
}
