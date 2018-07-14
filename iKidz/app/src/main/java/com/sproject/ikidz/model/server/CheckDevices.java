package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_CheckDevices;
import com.sproject.ikidz.model.entity.DeviceInfo;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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