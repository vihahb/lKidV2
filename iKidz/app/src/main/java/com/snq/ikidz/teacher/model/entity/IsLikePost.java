package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class IsLikePost {
    @Expose
    private int is_like_post;

    public IsLikePost(int is_like_post) {
        this.is_like_post = is_like_post;
    }

    public int getIs_like_post() {
        return is_like_post;
    }

    public void setIs_like_post(int is_like_post) {
        this.is_like_post = is_like_post;
    }

    @Override
    public String toString() {
        return "IsLikePost{" +
                "is_like_post=" + is_like_post +
                '}';
    }
}
