package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class DataUpdateAttendance {
    @Expose
    private List<ValueAttendance> attendances;
    //TODO("Định dạng YYYY-MM-DD")
    @Expose
    private String attendance_date;
    @Expose
    private int class_id;

    public DataUpdateAttendance(List<ValueAttendance> attendances, String attendance_date, int class_id) {
        this.attendances = attendances;
        this.attendance_date = attendance_date;
        this.class_id = class_id;
    }

    public List<ValueAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<ValueAttendance> attendances) {
        this.attendances = attendances;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "DataUpdateAttendance{" +
                "attendances=" + attendances +
                ", attendance_date='" + attendance_date + '\'' +
                ", class_id=" + class_id +
                '}';
    }
}
