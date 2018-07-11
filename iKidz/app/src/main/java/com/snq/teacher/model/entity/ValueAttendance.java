package com.snq.teacher.model.entity;

import com.google.gson.annotations.Expose;

public class ValueAttendance {
    @Expose
    private String student_id;
    @Expose
    private String checked;

    public ValueAttendance(String student_id, String checked) {
        this.student_id = student_id;
        this.checked = checked;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
