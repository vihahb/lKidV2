package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_GetComment;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.GetCommentParamsEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetCommentModel extends AbsICmd {

    private BasicModel basicModel = new BasicModel();
    GetCommentParamsEntity params;

    public GetCommentModel(GetCommentParamsEntity params) {
        this.params = params;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "get-comment-by-news-id";
        basicModel.requestServer.postApi(url, JsonHelper.toJson(params), token, new ResponseHandle<RESP_GetComment>(RESP_GetComment.class) {
            @Override
            protected void onSuccess(RESP_GetComment obj) {
                GetCommentModel.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetCommentModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_GetComment obj);

    protected abstract void onError(ErrorEntity s);
}