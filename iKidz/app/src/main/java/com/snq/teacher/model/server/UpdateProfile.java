package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_Status;
import com.snq.teacher.model.entity.DataProfilePostEntify;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class UpdateProfile extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    DataProfilePostEntify data;

    public UpdateProfile(DataProfilePostEntify data) {
        this.data = data;
        run();
    }
    //http://v3.ikidz.edu.vn/api/v3/attendance-comeback

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.BASE_URL);
        String url = link_api + "/api/v1/add-shool-report/year";

        basicModel.requestServer.postApi(url, JsonHelper.toJson(data), token, new ResponseHandle<RESP_Status>(RESP_Status.class) {
            @Override
            protected void onSuccess(RESP_Status obj) {
                UpdateProfile.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateProfile.this.onError(error);
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
