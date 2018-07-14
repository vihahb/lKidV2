package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_ListParent;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetListParent extends AbsICmd {

    int id;
    BasicModel basicModel = new BasicModel();

    public GetListParent(int id) {
        this.id = id;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "parentsofstudent/" + id;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_ListParent>(RESP_ListParent.class) {
            @Override
            protected void onSuccess(RESP_ListParent obj) {
                GetListParent.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetListParent.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_ListParent news);

    protected abstract void onError(ErrorEntity s);
}

