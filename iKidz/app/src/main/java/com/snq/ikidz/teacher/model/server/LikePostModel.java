package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_like;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.model.entity.SendLikePost;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class LikePostModel extends AbsICmd {

    private BasicModel basicModel = new BasicModel();
    SendLikePost likePost;

    public LikePostModel(SendLikePost likePost) {
        this.likePost = likePost;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "like";
        basicModel.requestServer.postApi(url, JsonHelper.toJson(likePost), token, new ResponseHandle<RESP_like>(RESP_like.class) {
            @Override
            protected void onSuccess(RESP_like obj) {
                LikePostModel.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                LikePostModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_like obj);

    protected abstract void onError(ErrorEntity s);
}
