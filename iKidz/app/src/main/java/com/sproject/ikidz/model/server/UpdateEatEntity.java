package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_ResultValue;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.UpdateValueEat;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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