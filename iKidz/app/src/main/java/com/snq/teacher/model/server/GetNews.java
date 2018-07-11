package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataNews;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.RequestNews;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

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
            protected void onError(ErrorEntity error) {
                GetNews.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataNews news);

    protected abstract void onError(ErrorEntity s);
}
