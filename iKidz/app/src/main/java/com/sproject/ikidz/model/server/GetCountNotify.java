package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_CountNotify;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetCountNotify extends AbsICmd {
    String token, link_api;
    BasicModel basicModel = new BasicModel();
    int class_id, type;

    public GetCountNotify(String token, String link_api, int class_id, int type) {
        this.token = token;
        this.link_api = link_api;
        this.class_id = class_id;
        this.type = type;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-number-notify-unread-of-module-for-teacher?class_id=" + class_id + "&type=" + type;
        basicModel.requestServer.postApi(url, "{}", token, new ResponseHandle<RESP_CountNotify>(RESP_CountNotify.class) {
            @Override
            protected void onSuccess(RESP_CountNotify obj) {
                GetCountNotify.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetCountNotify.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_CountNotify notify);

    protected abstract void onError(ErrorEntity s);
}
