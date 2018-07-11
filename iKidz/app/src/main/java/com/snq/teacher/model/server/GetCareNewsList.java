package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_DataCareNews;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

public abstract class GetCareNewsList extends AbsICmd {

    BasicModel basicModel = new BasicModel();

    public GetCareNewsList() {
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "get-list-child-care-news";
        String json = "{\t\"keyword\":\"\",\t\"per_page\":0,\t\"page\":0}";

        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataCareNews>(RESP_DataCareNews.class) {
            @Override
            protected void onSuccess(RESP_DataCareNews obj) {
                GetCareNewsList.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetCareNewsList.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataCareNews news);

    protected abstract void onError(ErrorEntity s);

}