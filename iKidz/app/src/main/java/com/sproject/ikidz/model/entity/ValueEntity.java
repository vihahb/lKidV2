package com.sproject.ikidz.model.entity;

import com.google.gson.annotations.Expose;

public class ValueEntity {
    @Expose
    private String value;
    @Expose
    private String count;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ValueEntity{" +
                "value='" + value + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
