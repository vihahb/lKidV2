package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ProfileInfoEntity;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetProfile extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    int studentId;

    public GetProfile(int studentId) {
        this.studentId = studentId;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-school-report/year/" + id;
        String json = "{\"student_id\":\"" + studentId + "\"}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_ProfileInfoEntity>(RESP_ProfileInfoEntity.class) {
            @Override
            protected void onSuccess(RESP_ProfileInfoEntity obj) {
                GetProfile.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetProfile.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_ProfileInfoEntity news);

    protected abstract void onError(ErrorEntity s);

}