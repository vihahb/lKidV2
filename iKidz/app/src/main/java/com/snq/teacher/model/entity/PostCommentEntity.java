package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostCommentEntity {

    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("type_comment")
    @Expose
    private Integer typeComment;

    public PostCommentEntity(String comment, Integer postId, Integer typeComment) {
        this.comment = comment;
        this.postId = postId;
        this.typeComment = typeComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getTypeComment() {
        return typeComment;
    }

    public void setTypeComment(Integer typeComment) {
        this.typeComment = typeComment;
    }

}