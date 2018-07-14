package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ResultValue;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.model.entity.UpdateValueEat;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class UpdateEatEntity extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    UpdateValueEat data;

    public UpdateEatEntity(UpdateValueEat data) {
        this.data = data;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);

        String url = link_api + "add-activity/eat";
        data.setClassId(id);
        basicModel.requestServer.postApi(url, JsonHelper.toJson(data), token, new ResponseHandle<RESP_ResultValue>(RESP_ResultValue.class) {
            @Override
            protected void onSuccess(RESP_ResultValue obj) {
                UpdateEatEntity.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateEatEntity.this.onError(error);
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