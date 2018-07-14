package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_LogToEat;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.PostParamsEat;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetLogTicketToEat extends AbsICmd {

    String link_api, token;
    BasicModel basicModel = new BasicModel();
    PostParamsEat paramsEat;

    public GetLogTicketToEat(PostParamsEat paramsEat, String token, String link_api) {
        this.paramsEat = paramsEat;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-list-ticket-of-class-by-month";
        String json = JsonHelper.toJson(paramsEat);
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_LogToEat>(RESP_LogToEat.class) {
            @Override
            protected void onSuccess(RESP_LogToEat obj) {
                GetLogTicketToEat.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetLogTicketToEat.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_LogToEat data);

    protected abstract void onError(ErrorEntity s);
}