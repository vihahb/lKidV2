package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataSleep;
import com.sproject.ikidz.model.RESP.RESP_ProfileInfoEntity;
import com.sproject.ikidz.model.RESP.RESP_SampleReview;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetSampleReview extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    int type;

    public GetSampleReview(int type) {
        this.type = type;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-sample-sentences-review?type=" + type;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_SampleReview>(RESP_SampleReview.class) {
            @Override
            protected void onSuccess(RESP_SampleReview obj) {
                GetSampleReview.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetSampleReview.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_SampleReview news);

    protected abstract void onError(ErrorEntity s);

}