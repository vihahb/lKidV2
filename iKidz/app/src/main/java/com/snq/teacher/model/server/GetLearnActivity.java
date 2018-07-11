package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataLearnActivity;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetLearnActivity extends AbsICmd {

    int id, page;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetLearnActivity(int page, int id, String token, String link_api) {
        this.page = page;
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-activity/learn/" + id + "?page=" + page;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_DataLearnActivity>(RESP_DataLearnActivity.class) {
            @Override
            protected void onSuccess(RESP_DataLearnActivity obj) {
                GetLearnActivity.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetLearnActivity.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataLearnActivity news);

    protected abstract void onError(ErrorEntity s);
}