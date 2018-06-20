package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_ForeignInfo;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetInfoForeign extends AbsICmd {

    String link_api, token;
    int id;
    BasicModel basicModel = new BasicModel();

    public GetInfoForeign(String link_api, String token, int id) {
        this.link_api = link_api;
        this.token = token;
        this.id = id;
        run();
    }

    @Override
    protected void invoke() {
        int class_id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "extracurricular-teacher/" + id;
        String json = "{\"class_id\":" + class_id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_ForeignInfo>(RESP_ForeignInfo.class) {
            @Override
            protected void onSuccess(RESP_ForeignInfo obj) {
                GetInfoForeign.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetInfoForeign.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_ForeignInfo data);

    protected abstract void onError(ErrorEntity s);
}
