package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_CurrentClass;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.SendGetClassInfo;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetCurrentClass extends AbsICmd {

    String link_api, token;
    SendGetClassInfo getClass;
    BasicModel basicModel = new BasicModel();

    public GetCurrentClass(String link_api, String token, SendGetClassInfo getClass) {
        this.link_api = link_api;
        this.token = token;
        this.getClass = getClass;
        run();
    }

    @Override
    protected void invoke() {
        String json = JsonHelper.toJson(getClass);
        String url = link_api + "studentsofclass";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_CurrentClass>(RESP_CurrentClass.class) {
            @Override
            protected void onSuccess(RESP_CurrentClass obj) {
                GetCurrentClass.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetCurrentClass.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_CurrentClass data);

    protected abstract void onError(ErrorEntity s);
}
