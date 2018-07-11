package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_DataSleep;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetSleepList extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    String date;

    public GetSleepList(String date) {
        this.date = date;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-activity/sleep/" + id;
        String json = "{\"date\":\"" + date + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataSleep>(RESP_DataSleep.class) {
            @Override
            protected void onSuccess(RESP_DataSleep obj) {
                GetSleepList.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetSleepList.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataSleep news);

    protected abstract void onError(ErrorEntity s);

}

