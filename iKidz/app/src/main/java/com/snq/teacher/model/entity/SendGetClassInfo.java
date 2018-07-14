package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendGetClassInfo {

    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("type")
    @Expose
    private Integer type;

    public SendGetClassInfo(Integer classId, Integer perPage, Integer page, String keyword, Integer type) {
        this.classId = classId;
        this.perPage = perPage;
        this.page = page;
        this.keyword = keyword;
        this.type = type;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
