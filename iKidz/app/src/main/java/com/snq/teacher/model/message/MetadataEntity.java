package com.snq.teacher.model.message;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

public class MetadataEntity {
    @Expose
    public HashMap<String, String> authorizedUsers;
    @Expose
    public Long createdAt;
    @Expose
    public String createdBy;
    @Expose
    public String receiver;
    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public String type;

    public MetadataEntity() {
    }

    public MetadataEntity(HashMap<String, String> authorizedUsers, Long createdAt, String createdBy, String receiver, String id, String name, String type) {
        this.authorizedUsers = authorizedUsers;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.receiver = receiver;
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public HashMap<String, String> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(HashMap<String, String> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MetadataEntity{" +
                "authorizedUsers=" + authorizedUsers +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", receiver='" + receiver + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
