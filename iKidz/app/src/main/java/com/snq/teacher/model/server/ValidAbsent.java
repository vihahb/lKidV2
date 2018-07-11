package com.snq.teacher.model.server;
import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_Status;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.Commons.Constants;
import com.snq.teacher.sdk.Utils.SharedUtils;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;
public abstract class ValidAbsent extends AbsICmd {
    int id, state;
    BasicModel basicModel = new BasicModel();

    public ValidAbsent(int id, int state) {
        this.state = state;
        this.id = id;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);

        String url = link_api + "change-status-absent";
        String json = "{\"id\":" + id + ", \"status\":" + state + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_Status>(RESP_Status.class) {
            @Override
            protected void onSuccess(RESP_Status obj) {
                ValidAbsent.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                ValidAbsent.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_Status news);

    protected abstract void onError(ErrorEntity s);
}

