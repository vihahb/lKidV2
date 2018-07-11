package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataSleep;
import com.snq.teacher.model.RESP.RESP_ProfileInfoEntity;
import com.snq.teacher.model.RESP.RESP_SampleReview;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

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