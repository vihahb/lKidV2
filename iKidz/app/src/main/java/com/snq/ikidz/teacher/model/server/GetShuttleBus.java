package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ShuttlesBus;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetShuttleBus extends AbsICmd {

    int id;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetShuttleBus(int id, String token, String link_api) {
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-register-pickup-point-for-com.snq.ikidz.teacher";
        String json = "{\"class_id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_ShuttlesBus>(RESP_ShuttlesBus.class) {
            @Override
            protected void onSuccess(RESP_ShuttlesBus obj) {
                GetShuttleBus.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetShuttleBus.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_ShuttlesBus news);

    protected abstract void onError(ErrorEntity s);
}


