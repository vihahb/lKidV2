package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SchoolByDistrict implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code_school")
    @Expose
    private String codeSchool;
    @SerializedName("name_school")
    @Expose
    private String nameSchool;
    @SerializedName("link_api")
    @Expose
    private String linkApi;

    /**
     * No args constructor for use in serialization
     */
    public SchoolByDistrict() {
    }

    /**
     * @param id
     * @param codeSchool
     * @param linkApi
     * @param nameSchool
     */
    public SchoolByDistrict(Integer id, String codeSchool, String nameSchool, String linkApi) {
        super();
        this.id = id;
        this.codeSchool = codeSchool;
        this.nameSchool = nameSchool;
        this.linkApi = linkApi;
    }

    public SchoolByDistrict(Integer id, String nameSchool) {
        this.id = id;
        this.nameSchool = nameSchool;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeSchool() {
        return codeSchool;
    }

    public void setCodeSchool(String codeSchool) {
        this.codeSchool = codeSchool;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }

    public String getLinkApi() {
        return linkApi;
    }

    public void setLinkApi(String linkApi) {
        this.linkApi = linkApi;
    }

    @Override
    public String toString() {
        return "SchoolByDistrict{" +
                "id=" + id +
                ", codeSchool='" + codeSchool + '\'' +
                ", nameSchool='" + nameSchool + '\'' +
                ", linkApi='" + linkApi + '\'' +
                '}';
    }
}