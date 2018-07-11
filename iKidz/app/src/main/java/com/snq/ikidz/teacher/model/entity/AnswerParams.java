package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class AnswerParams {
    @Expose
    private int question_id;
    @Expose
    private String value;
    @Expose
    private int poll_id;

    public AnswerParams(int question_id, String value, int poll_id) {
        this.question_id = question_id;
        this.value = value;
        this.poll_id = poll_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    @Override
    public String toString() {
        return "AnswerParams{" +
                "question_id=" + question_id +
                ", value='" + value + '\'' +
                ", poll_id=" + poll_id +
                '}';
    }
}
