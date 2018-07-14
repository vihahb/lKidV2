package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_CommentLearn;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetLearnCmtList extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    String date;

    public GetLearnCmtList(String date) {
        this.date = date;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-school-report/date/" + id;
        String json = "{\"date\":\"" + date + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_CommentLearn>(RESP_CommentLearn.class) {
            @Override
            protected void onSuccess(RESP_CommentLearn obj) {
                GetLearnCmtList.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetLearnCmtList.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_CommentLearn news);

    protected abstract void onError(ErrorEntity s);

}