package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_ForeignData;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetForeignActivity extends AbsICmd {

    String token;
    BasicModel basicModel = new BasicModel();


    public GetForeignActivity(String token) {
        this.token = token;
        run();
    }

    @Override
    protected void invoke() {
        String url = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/api/" + basicModel.OLD_VERSION_API + "list-extracurricular-teacher";
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
