package com.sproject.ikidz.model.server;
import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Status;
import com.sproject.ikidz.model.entity.DataUpdateAttendance;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;
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
