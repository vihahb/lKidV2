package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.PhoneBookData;

import java.util.List;

public class RESP_PhoneBook extends RESP_Basic {
    @Expose
    private List<PhoneBookData> data;

    public List<PhoneBookData> getData() {
        return data;
    }

    public void setData(List<PhoneBookData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_PhoneBook{" +
                "data=" + data +
                '}';
    }
}
