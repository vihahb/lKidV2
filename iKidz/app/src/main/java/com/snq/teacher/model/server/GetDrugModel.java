package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_GetDrug;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.SendGetClassInfo;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetDrugModel extends AbsICmd {
    String link_api, token;
    SendGetClassInfo getDrug;
    BasicModel basicModel = new BasicModel();

    public GetDrugModel(SendGetClassInfo getAbsent, String link_api, String token) {
        this.getDrug = getAbsent;
        this.link_api = link_api;
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String json = JsonHelper.toJson(getDrug);
        String urrl = link_api + "drug-list-of-class";
        basicModel.requestServer.postApi(urrl, json, token, new ResponseHandle<RESP_GetDrug>(RESP_GetDrug.class) {
            @Override
            protected void onSuccess(RESP_GetDrug obj) {
                GetDrugModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetDrugModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_GetDrug notify);

    protected abstract void onError(ErrorEntity s);
}

