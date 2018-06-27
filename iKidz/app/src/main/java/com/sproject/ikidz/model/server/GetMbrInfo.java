package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_MbrInfo;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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