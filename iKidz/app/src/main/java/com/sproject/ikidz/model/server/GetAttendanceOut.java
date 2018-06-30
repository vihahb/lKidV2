package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_AttendanceOut;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetAttendanceOut extends AbsICmd {

    String token;
    int class_id;
    BasicModel basicModel = new BasicModel();

    public GetAttendanceOut(String token, int class_id) {
        this.token = token;
        this.class_id = class_id;
        run();
    }

    @Override
    protected void invoke() {
        String url = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/api/" + basicModel.OLD_VERSION_API + "attendance-comeback-student-list/" + class_id;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_AttendanceOut>(RESP_AttendanceOut.class) {
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
