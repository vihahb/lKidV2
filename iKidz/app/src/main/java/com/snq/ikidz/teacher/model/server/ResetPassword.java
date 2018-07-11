package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Basic;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class ResetPassword extends AbsICmd {

    String link_api, email;
    BasicModel basicModel = new BasicModel();

    public ResetPassword(String link_api, String email) {
        this.link_api = link_api;
        this.email = email;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "resetpassword";
        String json = "{\"email\":\"" + email + "\"}";
        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_Basic>(RESP_Basic.class) {
            @Override
            protected void onSuccess(RESP_Basic obj) {
                ResetPassword.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                ResetPassword.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_Basic basic);

    protected abstract void onError(ErrorEntity s);
}
