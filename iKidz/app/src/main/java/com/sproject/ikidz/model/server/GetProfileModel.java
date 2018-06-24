package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataProfile;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.SendGetClassInfo;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetProfileModel extends AbsICmd {
    String link_api, token;
    SendGetClassInfo getDrug;
    BasicModel basicModel = new BasicModel();

    public GetProfileModel(SendGetClassInfo getAbsent, String link_api, String token) {
        this.getDrug = getAbsent;
        this.link_api = link_api;
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String json = JsonHelper.toJson(getDrug);
        String urrl = link_api + "studentsofclass";
        basicModel.requestServer.postApi(urrl, json, token, new ResponseHandle<RESP_DataProfile>(RESP_DataProfile.class) {
            @Override
            protected void onSuccess(RESP_DataProfile obj) {
                GetProfileModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetProfileModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_DataProfile notify);

    protected abstract void onError(ErrorEntity s);
}