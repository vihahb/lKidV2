package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_InfoCampagn;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetInfoCampaign extends AbsICmd {

    int id;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetInfoCampaign(int id, String token, String link_api) {
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "/api/v1/get-detail-question-poll";
        String json = "{\"question_id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_InfoCampagn>(RESP_InfoCampagn.class) {
            @Override
            protected void onSuccess(RESP_InfoCampagn obj) {
                GetInfoCampaign.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetInfoCampaign.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_InfoCampagn news);

    protected abstract void onError(ErrorEntity s);
}