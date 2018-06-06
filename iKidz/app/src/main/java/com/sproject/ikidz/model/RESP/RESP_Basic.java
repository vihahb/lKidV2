package com.sproject.ikidz.model.RESP;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 9:54 PM
 * Email: vihahb@gmail.com
 */
public class RESP_Basic implements Serializable {

    @Expose
    private int errorCode;
    @Expose
    private String errorDesc;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString() {
        return "RESP_Basic{" +
                "errorCode=" + errorCode +
                ", errorDesc='" + errorDesc + '\'' +
                '}';
    }
}
