package com.snq.teacher.model.RESP;

import com.google.gson.annotations.Expose;
import com.snq.teacher.model.entity.DataUser;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:26 AM
 * Email: vihahb@gmail.com
 */
public class RESP_Login extends RESP_Basic {

    @Expose
    private DataUser data;

    public DataUser getData() {
        return data;
    }

    public void setData(DataUser data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Login{" +
                "data=" + data +
                '}';
    }
}
