package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_GetAbsent;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.SendGetAbsent;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetAbsentModel extends AbsICmd {

    String link_api, token;
    SendGetAbsent getAbsent;
    BasicModel basicModel = new BasicModel();

    public GetAbsentModel(SendGetAbsent getAbsent, String link_api, String token) {
        this.getAbsent = getAbsent;
        this.link_api = link_api;
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String json = JsonHelper.toJson(getAbsent);
        String urrl = link_api + "leave-school-of-class";
        basicModel.requestServer.postApi(urrl, json, token, new ResponseHandle<RESP_GetAbsent>(RESP_GetAbsent.class) {
            @Override
            protected void onSuccess(RESP_GetAbsent obj) {
                GetAbsentModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetAbsentModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_GetAbsent notify);
    protected abstract void onError(ErrorEntity s);
}
