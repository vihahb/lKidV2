package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Basic;
import com.snq.ikidz.teacher.model.entity.DataParamsAddLearnEntity;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class CreateOrUpdateLearnActivity extends AbsICmd {

    DataParamsAddLearnEntity params;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public CreateOrUpdateLearnActivity(DataParamsAddLearnEntity params, String token, String link_api) {
        this.params = params;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "add-activity/learn";
        String json = JsonHelper.toJson(params);
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_Basic>(RESP_Basic.class) {
            @Override
            protected void onSuccess(RESP_Basic obj) {
                CreateOrUpdateLearnActivity.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                CreateOrUpdateLearnActivity.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_Basic news);

    protected abstract void onError(ErrorEntity s);
}