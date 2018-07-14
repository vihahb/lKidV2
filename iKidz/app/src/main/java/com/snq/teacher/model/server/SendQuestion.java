package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataAnswer;
import com.snq.teacher.model.entity.AnswerParams;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class SendQuestion extends AbsICmd {

    AnswerParams params;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public SendQuestion(AnswerParams params, String token, String link_api) {
        this.params = params;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "/api/v1/user-post-poll";
        String json = JsonHelper.toJson(params);
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataAnswer>(RESP_DataAnswer.class) {
            @Override
            protected void onSuccess(RESP_DataAnswer obj) {
                SendQuestion.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                SendQuestion.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataAnswer news);

    protected abstract void onError(ErrorEntity s);
}