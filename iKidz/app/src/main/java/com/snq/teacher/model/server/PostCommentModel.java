package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_PostComment;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.PostCommentEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

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
