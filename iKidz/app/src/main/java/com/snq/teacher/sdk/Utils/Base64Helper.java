package com.snq.teacher.sdk.Utils;

import android.support.annotation.NonNull;

import org.apache.commons.codec.binary.Base64;

public class Base64Helper {

    public static String getEncode(@NonNull String s) {
        return new String(Base64.encodeBase64(s.getBytes()));
    }

    public static String getDecode(@NonNull String s) {
        return new String(Base64.decodeBase64(s.getBytes()));
    }
}