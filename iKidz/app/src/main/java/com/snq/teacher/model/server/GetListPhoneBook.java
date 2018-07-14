package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_PhoneBook;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

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