package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataNews;
import com.sproject.ikidz.model.entity.RequestNews;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetNews extends AbsICmd {

    int page;
    String token, link_api;
    RequestNews requestNews;
    private BasicModel basicModel = new BasicModel();

    public GetNews(int page, String token, String link_api, RequestNews requestNews) {
        this.page = page;
        this.token = token;
        this.link_api = link_api;
        this.requestNews = requestNews;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "news?page=" + page;
        String json = JsonHelper.toJson(requestNews);
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataNews>(RESP_DataNews.class) {
            @Override
            protected void onSuccess(RESP_DataNews obj) {
                GetNews.this.onSucces(obj);
            }

            @Override
            protected void onError(String error) {
                GetNews.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(String message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataNews news);

    protected abstract void onError(String s);
}
