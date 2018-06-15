package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsEntity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cat_id")
    @Expose
    private Object catId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("summary")
    @Expose
    private Object summary;
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
    @SerializedName("album")
    @Expose
    private List<AlbumEntity> album;
    @SerializedName("is_like_post")
    @Expose
    private Integer isLikePost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCatId() {
        return catId;
    }

    public void setCatId(Object catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
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

    public List<AlbumEntity> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumEntity> album) {
        this.album = album;
    }

    public Integer getIsLikePost() {
        return isLikePost;
    }

    public void setIsLikePost(Integer isLikePost) {
        this.isLikePost = isLikePost;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", catId=" + catId +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", summary=" + summary +
                ", countComment='" + countComment + '\'' +
                ", countLike='" + countLike + '\'' +
                ", type='" + type + '\'' +
                ", isComment='" + isComment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", classId='" + classId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", image='" + image + '\'' +
                ", avatar='" + avatar + '\'' +
                ", album=" + album +
                ", isLikePost=" + isLikePost +
                '}';
    }
}