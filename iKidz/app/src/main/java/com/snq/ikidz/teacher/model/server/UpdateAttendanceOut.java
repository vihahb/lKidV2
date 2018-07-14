package com.snq.ikidz.teacher.model.server;
import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Status;
import com.snq.ikidz.teacher.model.entity.DataUpdateAttendance;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;
public abstract class UpdateAttendanceOut extends AbsICmd {
    BasicModel basicModel = new BasicModel();
    DataUpdateAttendance data;

    public UpdateAttendanceOut(DataUpdateAttendance data) {
        this.data = data;
        run();
    }
    //http://v3.ikidz.edu.vn/api/v3/attendance-comeback

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "attendance-comeback";

        data.setClass_id(id);

        basicModel.requestServer.postApi(url, JsonHelper.toJson(data), token, new ResponseHandle<RESP_Status>(RESP_Status.class) {
            @Override
            protected void onSuccess(RESP_Status obj) {
                UpdateAttendanceOut.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                UpdateAttendanceOut.this.onError(error);
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
