package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_NewsInfo;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetNewsInfo extends AbsICmd {

    int id;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetNewsInfo(int id, String token, String link_api) {
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-news-detail";
        String json = "{\"id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_NewsInfo>(RESP_NewsInfo.class) {
            @Override
            protected void onSuccess(RESP_NewsInfo obj) {
                GetNewsInfo.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetNewsInfo.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_NewsInfo news);

    protected abstract void onError(ErrorEntity s);
}
