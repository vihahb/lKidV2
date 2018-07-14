package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Status;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class UpdateTimePick extends AbsICmd {
    int id;
    String timePick;
    BasicModel basicModel = new BasicModel();

    public UpdateTimePick(int id, String timePick) {
        this.timePick = timePick;
        this.id = id;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);

        String url = link_api + "update-time-pick-for-register-more-time";
        String json = "{\"id\":" + id + ", \"time_pick\":\"" + timePick + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_Status>(RESP_Status.class) {
            @Override
            protected void onSuccess(RESP_Status obj) {
                UpdateTimePick.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateTimePick.this.onError(error);
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