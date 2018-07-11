package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_ResultValue;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.UpdateSleepAndOtherEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class UpdateSleepChild extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    UpdateSleepAndOtherEntity data;

    public UpdateSleepChild(UpdateSleepAndOtherEntity data) {
        this.data = data;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);

        String url = link_api + "add-activity/sleep";
        data.setClassId(id);
        basicModel.requestServer.postApi(url, JsonHelper.toJson(data), token, new ResponseHandle<RESP_ResultValue>(RESP_ResultValue.class) {
            @Override
            protected void onSuccess(RESP_ResultValue obj) {
                UpdateSleepChild.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateSleepChild.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_ResultValue news);

    protected abstract void onError(ErrorEntity s);
}