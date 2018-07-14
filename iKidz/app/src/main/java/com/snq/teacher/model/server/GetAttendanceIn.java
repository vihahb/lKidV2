package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_AttendanceIn;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetAttendanceIn extends AbsICmd {

    String token, date;
    int class_id;
    BasicModel basicModel = new BasicModel();

    public GetAttendanceIn(String token, int class_id, String date) {
        this.token = token;
        this.date = date;
        this.class_id = class_id;
        run();
    }

    @Override
    protected void invoke() {
        String url = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/api/" + basicModel.OLD_VERSION_API + "attendance-student-list/" + class_id;
        String json = "{\"attendance_date\":\"" + date + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_AttendanceIn>(RESP_AttendanceIn.class) {
            @Override
            protected void onSuccess(RESP_AttendanceIn obj) {
                GetAttendanceIn.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetAttendanceIn.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_AttendanceIn data);

    protected abstract void onError(ErrorEntity s);
}
