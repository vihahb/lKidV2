package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Status;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;
public abstract class UpdateTimeSleep extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    String start, end;

    public UpdateTimeSleep(String start, String end) {
        this.start = start;
        this.end = end;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "add-setting-activity-sleep";

        String json = "{\"class_id\": " + id + ", \"start_time\": \"" + start + "\", \"end_time\": \"" + end + "\"}";

        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_Status>(RESP_Status.class) {
            @Override
            protected void onSuccess(RESP_Status obj) {
                UpdateTimeSleep.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateTimeSleep.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_Status news);

    protected abstract void onError(ErrorEntity s);
}