package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_MenuEat;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

public abstract class GetMenuList extends AbsICmd {

    BasicModel basicModel = new BasicModel();
    String date;

    public GetMenuList(String dates) {
        date = dates;
        run();
    }

    @Override
    protected void invoke() {
        String token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN);
        String link_api = SharedUtils.getInstance().getStringValue(Constants.LINK_API);
        int id = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID);


        String url = link_api + "get-daily-menu?date=" + date + "&class_id=" + id;
        basicModel.requestServer.postApi(url, "", token, new ResponseHandle<RESP_MenuEat>(RESP_MenuEat.class) {
            @Override
            protected void onSuccess(RESP_MenuEat obj) {
                GetMenuList.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetMenuList.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_MenuEat news);

    protected abstract void onError(ErrorEntity s);

}
