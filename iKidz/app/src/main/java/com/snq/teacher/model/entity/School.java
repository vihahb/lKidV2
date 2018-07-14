package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class School {

    @SerializedName("code_school")
    @Expose
    private String codeSchool;
    @SerializedName("name_school")
    @Expose
    private String nameSchool;
    @SerializedName("link_api")
    @Expose
    private String linkApi;
    @SerializedName("hotline_school")
    @Expose
    private String hotlineSchool;
    @SerializedName("address_school")
    @Expose
    private String addressSchool;

    /**
     * No args constructor for use in serialization
     */
    public School() {
    }

    /**
     * @param codeSchool
     * @param linkApi
     * @param nameSchool
     * @param addressSchool
     * @param hotlineSchool
     */
    public School(String codeSchool, String nameSchool, String linkApi, String hotlineSchool, String addressSchool) {
        super();
        this.codeSchool = codeSchool;
        this.nameSchool = nameSchool;
        this.linkApi = linkApi;
        this.hotlineSchool = hotlineSchool;
        this.addressSchool = addressSchool;
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

    public String getHotlineSchool() {
        return hotlineSchool;
    }

    public void setHotlineSchool(String hotlineSchool) {
        this.hotlineSchool = hotlineSchool;
    }

    public String getAddressSchool() {
        return addressSchool;
    }

    public void setAddressSchool(String addressSchool) {
        this.addressSchool = addressSchool;
    }

}