package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_MbrInfo;
import com.sproject.ikidz.model.RESP.RESP_StudentEntity;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetMbrList extends AbsICmd {

    BasicModel basicModel = new BasicModel();

    public GetMbrList() {
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);
        String url = link_api + "get-list-student-health?class_id=" + id;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_StudentEntity>(RESP_StudentEntity.class) {
            @Override
            protected void onSuccess(RESP_StudentEntity obj) {
                GetMbrList.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetMbrList.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_StudentEntity news);

    protected abstract void onError(ErrorEntity s);

}
