package com.sproject.ikidz.model;

import com.sproject.ikidz.sdk.Utils.RequestIon;
import com.sproject.ikidz.sdk.Utils.RequestOkHttp;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 9:53 PM
 * Email: vihahb@gmail.com
 */
public class BasicModel {


    public RequestOkHttp requestServer = new RequestOkHttp();
    public RequestIon requestIonServer = new RequestIon();

    public static String LINK_API = "http://manager.ikidz.edu.vn/api/";
    public static String VERSION_API = "v1/";
    public static String PROVINCE_API = "get-province";
    public static String DISTRICT_API = "get-district";
}
