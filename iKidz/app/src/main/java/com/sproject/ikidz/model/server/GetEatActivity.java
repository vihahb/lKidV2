package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataEat;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.Commons.Constants;
import com.sproject.ikidz.sdk.Utils.SharedUtils;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetEatActivity extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    String date, menu_time;

    public GetEatActivity(String dates, String menu_time) {
        this.menu_time = menu_time;
        date = dates;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);

        String url = link_api + "get-activity/eat/" + id + "?date=" + date + "&menu_time=" + menu_time;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_DataEat>(RESP_DataEat.class) {
            @Override
            protected void onSuccess(RESP_DataEat obj) {
                GetEatActivity.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetEatActivity.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataEat news);

    protected abstract void onError(ErrorEntity s);

}