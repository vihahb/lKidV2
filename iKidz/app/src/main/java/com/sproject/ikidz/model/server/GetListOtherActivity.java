package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataOtherActivity;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.ParamsOtherActivitysEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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