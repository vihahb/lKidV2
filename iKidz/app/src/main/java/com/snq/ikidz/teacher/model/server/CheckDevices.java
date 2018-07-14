package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_CheckDevices;
import com.snq.ikidz.teacher.model.entity.DeviceInfo;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;


public abstract class CheckDevices extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    DeviceInfo data;

    public CheckDevices(DeviceInfo data) {
        this.data = data;
        run();
    }
    //http://v3.ikidz.edu.vn/api/v3/attendance-comeback

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String url = "http://v3.ikidz.edu.vn/api/v3/check-device";
        String json = JsonHelper.toJson(data);
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_CheckDevices>(RESP_CheckDevices.class) {
            @Override
            protected void onSuccess(RESP_CheckDevices obj) {
                CheckDevices.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                CheckDevices.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_CheckDevices data);

    protected abstract void onError(ErrorEntity s);
}