package com.sproject.ikidz.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sproject.ikidz.outLibs.model.ItemPosition;

public class AlbumEntity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("album_id")
    @Expose
    private String albumId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;

    public AlbumEntity(String id, String albumId, String title, String image) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}