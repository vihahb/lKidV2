package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_CountNotify;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

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
        String url = link_api + "get-number-notify-unread-of-module-for-com.snq.ikidz.teacher?class_id=" + class_id + "&type=" + type;
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
