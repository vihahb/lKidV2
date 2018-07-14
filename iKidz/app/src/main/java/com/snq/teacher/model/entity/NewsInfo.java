package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("count_comment")
    @Expose
    private String countComment;
    @SerializedName("count_like")
    @Expose
    private String countLike;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("isComment")
    @Expose
    private String isComment;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("is_like_post")
    @Expose
    private Integer isLikePost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCountComment() {
        return countComment;
    }

    public void setCountComment(String countComment) {
        this.countComment = countComment;
    }

    public String getCountLike() {
        return countLike;
    }

    public void setCountLike(String countLike) {
        this.countLike = countLike;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsLikePost() {
        return isLikePost;
    }

    public void setIsLikePost(Integer isLikePost) {
        this.isLikePost = isLikePost;
    }

}