package com.snq.teacher.model.message;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserData {

    @Expose
    public String avatar;
    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public String news;
    @Expose
    public List<String> room;
    @Expose
    public List<String> sessions;

    public UserData() {
    }

    public UserData(String avatar, String id, String name, String news, List<String> room, List<String> sessions) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.news = news;
        this.room = room;
        this.sessions = sessions;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public List<String> getRoom() {
        return room;
    }

    public void setRoom(List<String> room) {
        this.room = room;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "avatar='" + avatar + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", news='" + news + '\'' +
                ", room=" + room +
                ", sessions=" + sessions +
                '}';
    }
}
