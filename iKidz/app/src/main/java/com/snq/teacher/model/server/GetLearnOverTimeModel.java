package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_LearnOverTime;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.SendGetClassInfo;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetLearnOverTimeModel extends AbsICmd {

    String link_api, token;
    SendGetClassInfo getDrug;
    BasicModel basicModel = new BasicModel();

    public GetLearnOverTimeModel(SendGetClassInfo getAbsent, String link_api, String token) {
        this.getDrug = getAbsent;
        this.link_api = link_api;
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String json = JsonHelper.toJson(getDrug);
        String urrl = link_api + "list-register-more-time-of-class";
        basicModel.requestServer.postApi(urrl, json, token, new ResponseHandle<RESP_LearnOverTime>(RESP_LearnOverTime.class) {
            @Override
            protected void onSuccess(RESP_LearnOverTime obj) {
                GetLearnOverTimeModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetLearnOverTimeModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_LearnOverTime notify);

    protected abstract void onError(ErrorEntity s);
}
