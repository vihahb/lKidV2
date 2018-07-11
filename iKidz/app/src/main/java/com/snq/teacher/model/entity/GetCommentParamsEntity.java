package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class GetCommentParamsEntity {
    @Expose
    private int post_id;
    @Expose
    private int type_comment;
    @Expose
    private int page;

    public GetCommentParamsEntity(int post_id, int type_comment, int page) {
        this.post_id = post_id;
        this.type_comment = type_comment;
        this.page = page;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getType_comment() {
        return type_comment;
    }

    public void setType_comment(int type_comment) {
        this.type_comment = type_comment;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "GetCommentParamsEntity{" +
                "post_id=" + post_id +
                ", type_comment=" + type_comment +
                ", page=" + page +
                '}';
    }
}
