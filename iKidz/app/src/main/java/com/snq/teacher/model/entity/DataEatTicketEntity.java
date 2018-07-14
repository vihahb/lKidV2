package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataEatTicketEntity {

    @SerializedName("detail_ticket")
    @Expose
    private List<EatTicketEntity> detail_ticket;
    @SerializedName("years")
    @Expose
    private List<Integer> years;
    @SerializedName("months")
    @Expose
    private List<Integer> months;
    @SerializedName("current_year")
    @Expose
    private String currentYear;
    @SerializedName("current_month")
    @Expose
    private String currentMonth;

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public void setMonths(List<Integer> months) {
        this.months = months;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public List<EatTicketEntity> getDetail_ticket() {
        return detail_ticket;
    }

    public void setDetail_ticket(List<EatTicketEntity> detail_ticket) {
        this.detail_ticket = detail_ticket;
    }
}