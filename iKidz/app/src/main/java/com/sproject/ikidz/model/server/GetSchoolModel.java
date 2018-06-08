package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_School;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:13 AM
 * Email: vihahb@gmail.com
 */
public abstract class GetSchoolModel extends AbsICmd {
    int schoolCode;
    BasicModel basicModel = new BasicModel();
    String json;

    public GetSchoolModel(int schoolCode) {
        this.schoolCode = schoolCode;
        run();
    }

    @Override
    protected void invoke() {
        String url = basicModel.MANAGER_LINK_API + basicModel.SCHOOL_API;
        if (schoolCode > -1)
            json = "{\"code\":" + schoolCode + "}";

        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_School>(RESP_School.class) {
            @Override
            protected void onSuccess(RESP_School obj) {
                GetSchoolModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(String error) {
                GetSchoolModel.this.onErrror(error);
            }
        });
    }

    @Override
    protected void exception(String message) {
        onErrror("UNKNOW Error");
    }

    protected abstract void onSuccess(RESP_School province);

    protected abstract void onErrror(String err);
}
