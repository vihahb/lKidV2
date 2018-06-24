package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Basic;
import com.sproject.ikidz.model.entity.DataParamsAddLearnEntity;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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