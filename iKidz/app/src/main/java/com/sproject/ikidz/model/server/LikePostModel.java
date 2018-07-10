package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_PostComment;
import com.sproject.ikidz.model.RESP.RESP_like;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.PostCommentEntity;
import com.sproject.ikidz.model.entity.SendLikePost;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

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
