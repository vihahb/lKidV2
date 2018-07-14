package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ResultValue;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetResult extends AbsICmd {

    int id;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetResult(int id, String token, String link_api) {
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "/api/v1/get-result-detail-poll";
        String json = "{\"question_id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_ResultValue>(RESP_ResultValue.class) {
            @Override
            protected void onSuccess(RESP_ResultValue obj) {
                GetResult.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetResult.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_ResultValue news);

    protected abstract void onError(ErrorEntity s);
}
