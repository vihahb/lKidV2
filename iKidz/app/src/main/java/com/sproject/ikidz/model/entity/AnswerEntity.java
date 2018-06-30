package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerEntity {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("poll_item_id")
    @Expose
    private Integer pollItemId;
    @SerializedName("poll_id")
    @Expose
    private Integer pollId;
    @SerializedName("value")
    @Expose
    private String value;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPollItemId() {
        return pollItemId;
    }

    public void setPollItemId(Integer pollItemId) {
        this.pollItemId = pollItemId;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}