package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilePostEntify {

    @SerializedName("absent_allow_1")
    @Expose
    private Integer absentAllow1;
    @SerializedName("absent_allow_2")
    @Expose
    private Integer absentAllow2;
    @SerializedName("absent_not_allow_1")
    @Expose
    private Integer absentNotAllow1;
    @SerializedName("absent_not_allow_2")
    @Expose
    private Integer absentNotAllow2;
    @SerializedName("absent_year")
    @Expose
    private Integer absentYear;
    @SerializedName("id_school_report")
    @Expose
    private String idSchoolReport;
    @SerializedName("review_health")
    @Expose
    private String reviewHealth;
    @SerializedName("review_morality")
    @Expose
    private String reviewMorality;
    @SerializedName("review_study")
    @Expose
    private String reviewStudy;
    @SerializedName("review_year")
    @Expose
    private String reviewYear;
    @SerializedName("title_1")
    @Expose
    private String title1;
    @SerializedName("title_2")
    @Expose
    private String title2;
    @SerializedName("title_year")
    @Expose
    private String titleYear;
    @SerializedName("upto_class")
    @Expose
    private String uptoClass;

    public ProfilePostEntify(Integer absentAllow1, Integer absentAllow2, Integer absentNotAllow1, Integer absentNotAllow2, Integer absentYear, String idSchoolReport, String reviewHealth, String reviewMorality, String reviewStudy, String reviewYear, String title1, String title2, String titleYear, String uptoClass) {
        this.absentAllow1 = absentAllow1;
        this.absentAllow2 = absentAllow2;
        this.absentNotAllow1 = absentNotAllow1;
        this.absentNotAllow2 = absentNotAllow2;
        this.absentYear = absentYear;
        this.idSchoolReport = idSchoolReport;
        this.reviewHealth = reviewHealth;
        this.reviewMorality = reviewMorality;
        this.reviewStudy = reviewStudy;
        this.reviewYear = reviewYear;
        this.title1 = title1;
        this.title2 = title2;
        this.titleYear = titleYear;
        this.uptoClass = uptoClass;
    }

    public Integer getAbsentAllow1() {
        return absentAllow1;
    }

    public void setAbsentAllow1(Integer absentAllow1) {
        this.absentAllow1 = absentAllow1;
    }

    public Integer getAbsentAllow2() {
        return absentAllow2;
    }

    public void setAbsentAllow2(Integer absentAllow2) {
        this.absentAllow2 = absentAllow2;
    }

    public Integer getAbsentNotAllow1() {
        return absentNotAllow1;
    }

    public void setAbsentNotAllow1(Integer absentNotAllow1) {
        this.absentNotAllow1 = absentNotAllow1;
    }

    public Integer getAbsentNotAllow2() {
        return absentNotAllow2;
    }

    public void setAbsentNotAllow2(Integer absentNotAllow2) {
        this.absentNotAllow2 = absentNotAllow2;
    }

    public Integer getAbsentYear() {
        return absentYear;
    }

    public void setAbsentYear(Integer absentYear) {
        this.absentYear = absentYear;
    }

    public String getIdSchoolReport() {
        return idSchoolReport;
    }

    public void setIdSchoolReport(String idSchoolReport) {
        this.idSchoolReport = idSchoolReport;
    }

    public String getReviewHealth() {
        return reviewHealth;
    }

    public void setReviewHealth(String reviewHealth) {
        this.reviewHealth = reviewHealth;
    }

    public String getReviewMorality() {
        return reviewMorality;
    }

    public void setReviewMorality(String reviewMorality) {
        this.reviewMorality = reviewMorality;
    }

    public String getReviewStudy() {
        return reviewStudy;
    }

    public void setReviewStudy(String reviewStudy) {
        this.reviewStudy = reviewStudy;
    }

    public String getReviewYear() {
        return reviewYear;
    }

    public void setReviewYear(String reviewYear) {
        this.reviewYear = reviewYear;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitleYear() {
        return titleYear;
    }

    public void setTitleYear(String titleYear) {
        this.titleYear = titleYear;
    }

    public String getUptoClass() {
        return uptoClass;
    }

    public void setUptoClass(String uptoClass) {
        this.uptoClass = uptoClass;
    }


    @Override
    public String toString() {
        return "ProfilePostEntify{" +
                "absentAllow1=" + absentAllow1 +
                ", absentAllow2=" + absentAllow2 +
                ", absentNotAllow1=" + absentNotAllow1 +
                ", absentNotAllow2=" + absentNotAllow2 +
                ", absentYear=" + absentYear +
                ", idSchoolReport='" + idSchoolReport + '\'' +
                ", reviewHealth='" + reviewHealth + '\'' +
                ", reviewMorality='" + reviewMorality + '\'' +
                ", reviewStudy='" + reviewStudy + '\'' +
                ", reviewYear='" + reviewYear + '\'' +
                ", title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", titleYear='" + titleYear + '\'' +
                ", uptoClass='" + uptoClass + '\'' +
                '}';
    }
}