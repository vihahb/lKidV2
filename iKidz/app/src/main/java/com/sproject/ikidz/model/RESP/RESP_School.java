package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;
import com.sproject.ikidz.model.entity.School;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:17 AM
 * Email: vihahb@gmail.com
 */
public class RESP_School extends RESP_Basic {
    @Expose
    private List<School> data;

    public List<School> getData() {
        return data;
    }

    public void setData(List<School> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_School{" +
                "data=" + data +
                '}';
    }
}
