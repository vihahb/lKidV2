package com.sproject.ikidz.sdk.callback;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.RESP.RESP_Basic;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.TextUtils;
import com.sproject.ikidz.view.activity.login.LoginActivity;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 9:53 PM
 * Email: vihahb@gmail.com
 */
public abstract class ResponseHandle<T extends RESP_Basic> {
    private static final String TAG = "ResponseHandle";

    private Class<T> tClass;

    protected ResponseHandle(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void onSuccess(String result) {
        try {
            Log.e(TAG, "result: " + result);
            if (TextUtils.isEmpty(result)) {
                onSuccess((T) null);
            } else {
                T t = JsonHelper.getObjectNoException(result, tClass);
                if (t != null & !TextUtils.isEmpty(t.getErrorDesc()) && t.getErrorCode() != 0) {
                    if (t.getErrorCode() == 12) {
                        iKidApplications.context.startActivity(new Intent(iKidApplications.context, LoginActivity.class));
                        Toast.makeText(iKidApplications.context, "Phiên làm việc của bạn đã hết hạn. Vui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                    } else
                        onError(new ErrorEntity(t.getErrorCode(), t.getErrorMessage()));
                } else {
                    onSuccess(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            onError(new ErrorEntity(-1, e.getMessage()));
        }
    }

    public void onError(Exception error) {
        onError(new ErrorEntity(-1, error.getMessage()));
    }

    protected abstract void onSuccess(T obj);

    protected abstract void onError(ErrorEntity error);
}
