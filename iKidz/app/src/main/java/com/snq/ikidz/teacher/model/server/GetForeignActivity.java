package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ForeignData;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetForeignActivity extends AbsICmd {

    String token;
    BasicModel basicModel = new BasicModel();


    public GetForeignActivity(String token) {
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String url = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/api/" + basicModel.OLD_VERSION_API + "list-extracurricular-com.snq.ikidz.teacher";
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_ForeignData>(RESP_ForeignData.class) {
            @Override
            protected void onSuccess(RESP_ForeignData obj) {
                GetForeignActivity.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetForeignActivity.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_ForeignData data);

    protected abstract void onError(ErrorEntity s);
}
