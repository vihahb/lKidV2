package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_CurrentClassTeacher;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetClassCurrentOfTeacher extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    private String token;
    private String link_api;

    public GetClassCurrentOfTeacher(String token, String link_api) {
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + basicModel.GET_CLASS_CURRENT_OF_TEACHER;
        basicModel.requestServer.postApi(url, "{}", token, new ResponseHandle<RESP_CurrentClassTeacher>(RESP_CurrentClassTeacher.class) {
            @Override
            protected void onSuccess(RESP_CurrentClassTeacher obj) {
                GetClassCurrentOfTeacher.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetClassCurrentOfTeacher.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_CurrentClassTeacher data);

    protected abstract void onError(ErrorEntity s);
}
