package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_PhoneBook;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetListPhoneBook extends AbsICmd {

    BasicModel basicModel = new BasicModel();

    public GetListPhoneBook() {
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "get-class-info-contact";
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_PhoneBook>(RESP_PhoneBook.class) {
            @Override
            protected void onSuccess(RESP_PhoneBook obj) {
                GetListPhoneBook.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetListPhoneBook.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_PhoneBook news);

    protected abstract void onError(ErrorEntity s);
}