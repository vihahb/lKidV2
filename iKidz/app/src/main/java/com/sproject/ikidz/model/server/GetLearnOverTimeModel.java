package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_LearnOverTime;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.SendGetClassInfo;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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
