package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataUser extends RealmObject {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("auth_id")
    @Expose
    @PrimaryKey
    private Integer authId;

    /**
     * No args constructor for use in serialization
     */
    public DataUser() {
    }

    /**
     * @param token
     * @param status
     * @param authId
     * @param user
     */
    public DataUser(User user, String status, String token, Integer authId) {
        super();
        this.user = user;
        this.status = status;
        this.token = token;
        this.authId = authId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "DataUser{" +
                "user=" + user +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", authId=" + authId +
                '}';
    }
}
