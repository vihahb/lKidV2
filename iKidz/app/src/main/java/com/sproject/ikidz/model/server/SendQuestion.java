package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Basic;
import com.sproject.ikidz.model.RESP.RESP_DataAnswer;
import com.sproject.ikidz.model.entity.AnswerParams;
import com.sproject.ikidz.model.entity.DataParamsAddLearnEntity;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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