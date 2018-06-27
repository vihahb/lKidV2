package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_ListParent;
import com.sproject.ikidz.model.RESP.RESP_PhoneBook;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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

