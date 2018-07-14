package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataMonth;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetLearnProgram extends AbsICmd {

    String date_start, date_end;
    BasicModel basicModel = new BasicModel();

    public GetLearnProgram(String date_start, String date_end) {
        this.date_start = date_start;
        this.date_end = date_end;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-study-program-of-class?class_id=" + id + "&start_date=" + date_start + "&end_date=" + date_end;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_DataMonth>(RESP_DataMonth.class) {
            @Override
            protected void onSuccess(RESP_DataMonth obj) {
                GetLearnProgram.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetLearnProgram.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataMonth news);

    protected abstract void onError(ErrorEntity s);
}

