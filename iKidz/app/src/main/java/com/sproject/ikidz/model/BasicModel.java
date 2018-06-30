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

    public static String LOGIN_API = "login";
    public static String MANAGER_LINK_API = "http://manager.ikidz.edu.vn/api/";
    public static String OLD_VERSION_API = "v1/";
    public static String VERSION_API = "v3/";
    public static String GET_CLASS_CURRENT_OF_TEACHER = "get-class-current-of-teacher";
    public static String PROVINCE_API = "get-province";
    public static String DISTRICT_API = "get-district";
    public static String SCHOOL_API = "get-school";
    public static String GET_SCHOOL_BY_DISTRICT = "get-school-by-district";
    public RequestOkHttp requestServer = new RequestOkHttp();
    public RequestIon requestIonServer = new RequestIon();
}
