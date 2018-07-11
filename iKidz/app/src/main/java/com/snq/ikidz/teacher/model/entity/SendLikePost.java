package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class SendLikePost {
    @Expose
    private int post_id;
    @Expose
    private int type_like;

    public SendLikePost(int post_id, int type_like) {
        this.post_id = post_id;
        this.type_like = type_like;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getType_like() {
        return type_like;
    }

    public void setType_like(int type_like) {
        this.type_like = type_like;
    }

    @Override
    public String toString() {
        return "SendLikePost{" +
                "post_id=" + post_id +
                ", type_like=" + type_like +
                '}';
    }
}
