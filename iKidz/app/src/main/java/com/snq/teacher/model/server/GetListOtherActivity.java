package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataOtherActivity;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.ParamsOtherActivitysEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetListOtherActivity extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    ParamsOtherActivitysEntity params;

    public GetListOtherActivity(ParamsOtherActivitysEntity params) {
        this.params = params;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-activity/other/" + id;

        String json = JsonHelper.toJson(params);

        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataOtherActivity>(RESP_DataOtherActivity.class) {
            @Override
            protected void onSuccess(RESP_DataOtherActivity obj) {
                GetListOtherActivity.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetListOtherActivity.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataOtherActivity news);

    protected abstract void onError(ErrorEntity s);

}