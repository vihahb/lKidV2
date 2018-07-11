package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataCampaign;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetDataCampaign extends AbsICmd {

    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetDataCampaign(String token, String link_api) {
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "/api/v1/get-poll-list";
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_DataCampaign>(RESP_DataCampaign.class) {
            @Override
            protected void onSuccess(RESP_DataCampaign obj) {
                GetDataCampaign.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetDataCampaign.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataCampaign news);

    protected abstract void onError(ErrorEntity s);
}