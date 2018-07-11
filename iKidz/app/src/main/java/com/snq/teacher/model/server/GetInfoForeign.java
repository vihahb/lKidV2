package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_ForeignInfo;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

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
