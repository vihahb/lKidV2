package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_AttendanceOut;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetAttendanceOut extends AbsICmd {

    String token, date;
    int class_id;
    BasicModel basicModel = new BasicModel();

    public GetAttendanceOut(String token, int class_id, String date) {
        this.token = token;
        this.class_id = class_id;
        this.date = date;
        run();
    }

    @Override
    protected void invoke() {
        String url = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/api/" + basicModel.OLD_VERSION_API + "attendance-comeback-student-list/" + class_id;
        String json = "{\"attendance_date\":\"" + date + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_AttendanceOut>(RESP_AttendanceOut.class) {
            @Override
            protected void onSuccess(RESP_AttendanceOut obj) {
                GetAttendanceOut.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetAttendanceOut.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_AttendanceOut data);

    protected abstract void onError(ErrorEntity s);

}
