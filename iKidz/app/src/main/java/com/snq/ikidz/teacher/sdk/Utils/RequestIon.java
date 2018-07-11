package com.snq.ikidz.teacher.sdk.Utils;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.util.Charsets;
import com.koushikdutta.ion.Ion;
import com.snq.ikidz.teacher.iKidApplications;
import com.snq.ikidz.teacher.model.RESP.RESP_Basic;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

import java.io.File;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 9:53 PM
 * Email: vihahb@gmail.com
 */
public class RequestIon {

    public RequestIon() {
    }

    public void getApi(String url, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("GET", url)
                .setTimeout(10000)
                .asString(Charsets.UTF_8)
                .setCallback((e, result) -> {
                    if (e != null)
                        responseHandle.onError(e);
                    else
                        responseHandle.onSuccess(result);
                });
    }

    public void postApi(String url, String body, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("POST", url)
                .setTimeout(30000)
                .setStringBody(body)
                .asString(Charsets.UTF_8)
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null)
                            responseHandle.onError(e);
                        else
                            responseHandle.onSuccess(result);
                    }
                });
    }

    public void putApi(String url, String body, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("PUT", url)
                .setTimeout(30000)
                .setStringBody(body)
                .asString(Charsets.UTF_8)
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null)
                            responseHandle.onError(e);
                        else
                            responseHandle.onSuccess(result);
                    }
                });
    }

    public void uploadImage(String url, File file, final ResponseHandle<RESP_Basic> responseHandle) {
        Ion.with(iKidApplications.context)
                .load("POST", url)
                .setTimeout(60000)
                .setMultipartFile("image", file)
                .asString(Charsets.UTF_8)
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null)
                            responseHandle.onError(e);
                        else
                            responseHandle.onSuccess(result);
                    }
                });
    }

//    public void uploadImage(String url, File file, String json, final ResponseHandle responseHandle) {
//        Ion.with(MyApplication.context)
//                .load("POST", url)
//                .setTimeout(60000)
//                .setMultipartFile("image", file)
//                .addMultipartParts(new StringPart("chophi", json))
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        if (e != null)
//                            responseHandle.onError(e);
//                        else
//                            responseHandle.onSuccess(result);
//                    }
//                });
//    }


    public void getApi(String url, String json, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("GET", url)
                .setTimeout(30000)
                .setStringBody(json)
                .asString(Charsets.UTF_8)
                .setCallback((e, result) -> {
                    if (e != null)
                        responseHandle.onError(e);
                    else
                        responseHandle.onSuccess(result);
                });
    }

    public void postApi(String url, String session, String body, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("POST", url)
                .setTimeout(30000)
                .setStringBody(body)
                .asString(Charsets.UTF_8)
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null)
                            responseHandle.onError(e);
                        else
                            responseHandle.onSuccess(result);
                    }
                });
    }

//    public void postApiHeaderParams(String url, String session, String body, final ResponseHandle<RESP_Ads> responseHandle) {
//        Ion.with(MyApplication.context)
//                .load("POST", url)
//                .setTimeout(30000)
//                .setHeader(Constants.PACKAGE_NAME, "com.xtel.azcare")
//                .setHeader(Constants.CMD, "GetAds")
//                .asString(Charsets.UTF_8)
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        if (e != null) {
//                            responseHandle.onError(e);
//                        } else {
//                            try {
//                                JSONObject jsonObject = new JSONObject(result);
//                                String config = jsonObject.getString("config").replace("\\", "").replace("\\n", "");
//                                AdsConfig adsConfig = JsonHelper.getObject(config, AdsConfig.class);
//                                Log.e("Ads config post", "Result: " + adsConfig.toString());
//                                AdmobConfig admobConfig = new AdmobConfig();
//                                admobConfig.setId(jsonObject.getInt("id"));
//                                admobConfig.setAppId(jsonObject.getInt("appId"));
//                                admobConfig.setState(jsonObject.getInt("state"));
//                                admobConfig.setConfig(adsConfig);
//                                String json = JsonHelper.toJson(admobConfig);
//                                Log.e("postApiHeaderParams", "OK: " + json);
//                                responseHandle.onSuccess(json);
//                            } catch (JSONException e1) {
//                                Log.e("postApiHeaderParams", "postApiHeaderParams: " + e1.toString());
//                            }
//                        }
//                    }
//                });
//    }

    public void putApi(String url, String session, String body, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("PUT", url)
                .setTimeout(30000)
                .setStringBody(body)
                .asString(Charsets.UTF_8)
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null)
                            responseHandle.onError(e);
                        else
                            responseHandle.onSuccess(result);
                    }
                });
    }

    public void deleteApi(String url, String session, String body, final ResponseHandle responseHandle) {
        Ion.with(iKidApplications.context)
                .load("DELETE", url)
                .setTimeout(30000)
                .setStringBody(body)
                .asString(Charsets.UTF_8)
                .setCallback((e, result) -> {
                    if (e != null)
                        responseHandle.onError(e);
                    else
                        responseHandle.onSuccess(result);
                });
    }


//    public void uploadImage(String url, String session, File file, final ResponseHandle<RESP_Upload_Image> responseHandle) {
//        Ion.with(iKidApplications.context)
//                .load("POST", url)
//                .setTimeout(60000)
//                .setMultipartFile("file", file)
//                .asString(Charsets.UTF_8)
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        if (e != null)
//                            responseHandle.onError(e);
//                        else
//                            responseHandle.onSuccess(result);
//                    }
//                });
//    }

}
