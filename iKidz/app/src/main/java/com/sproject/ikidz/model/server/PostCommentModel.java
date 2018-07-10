package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_PostComment;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.model.entity.PostCommentEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.JsonHelper;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class PostCommentModel extends AbsICmd {

    private BasicModel basicModel = new BasicModel();
    PostCommentEntity comment;

    public PostCommentModel(PostCommentEntity comment) {
        this.comment = comment;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        String url = link_api + "add-comment";
        basicModel.requestServer.postApi(url, JsonHelper.toJson(comment), token, new ResponseHandle<RESP_PostComment>(RESP_PostComment.class) {
            @Override
            protected void onSuccess(RESP_PostComment obj) {
                PostCommentModel.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                PostCommentModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_PostComment obj);

    protected abstract void onError(ErrorEntity s);
}
