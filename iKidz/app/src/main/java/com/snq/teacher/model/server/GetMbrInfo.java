package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_MbrInfo;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetMbrInfo extends AbsICmd {

    int id;
    BasicModel basicModel = new BasicModel();

    public GetMbrInfo(int id) {
        this.id = id;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);

        String url = link_api + "get-info-student-health";
        String json = "{\"student_id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_MbrInfo>(RESP_MbrInfo.class) {
            @Override
            protected void onSuccess(RESP_MbrInfo obj) {
                GetMbrInfo.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetMbrInfo.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_MbrInfo news);

    protected abstract void onError(ErrorEntity s);

}