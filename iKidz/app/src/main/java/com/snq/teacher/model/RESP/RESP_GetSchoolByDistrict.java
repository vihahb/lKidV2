package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.SchoolByDistrict;

import java.util.List;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:00 AM
 * Email: vihahb@gmail.com
 */
public class RESP_GetSchoolByDistrict extends RESP_Basic {
    @Expose
    private List<SchoolByDistrict> data;

    public List<SchoolByDistrict> getData() {
        return data;
    }

    public void setData(List<SchoolByDistrict> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_GetSchoolByDistrict{" +
                "data=" + data +
                '}';
    }
}
