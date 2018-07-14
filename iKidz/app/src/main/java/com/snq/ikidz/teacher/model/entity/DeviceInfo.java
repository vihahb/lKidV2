package com.snq.ikidz.teacher.model.entity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.provider.Settings;

import com.google.gson.annotations.Expose;
import com.snq.ikidz.teacher.iKidApplications;

public class DeviceInfo {
    @Expose
    private String device_id;
    @Expose
    private String platform;
    @Expose
    private String device_token;

    @SuppressLint("HardwareIds")
    public DeviceInfo(String device_token) {
        device_id = Settings.Secure.getString(iKidApplications.context.getContentResolver(), Settings.Secure.ANDROID_ID);
        platform = "Android " + Build.VERSION.RELEASE;
        this.device_token = device_token;
    }


    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "device_id='" + device_id + '\'' +
                ", platform='" + platform + '\'' +
                ", device_token='" + device_token + '\'' +
                '}';
    }
}