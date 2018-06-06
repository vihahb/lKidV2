package com.sproject.ikidz.model.server;

import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Province;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/7/2018
 * Time: 12:01 AM
 * Email: vihahb@gmail.com
 */
public abstract class DistrictModel extends AbsICmd {
    private BasicModel basicModel = new BasicModel();

    int pid;

    public DistrictModel(int pid) {
        this.pid = pid;
        run();
    }

    @Override
    protected void invoke() {
        String url = basicModel.LINK_API + basicModel.VERSION_API + basicModel.DISTRICT_API;
        iKidApplications.log("DistrictModel", url);
        String json = "{\"pid\":" + pid + "}";
        iKidApplications.log("DistrictModel", json);
        basicModel.requestServer.getApi(url, json, new ResponseHandle<RESP_Province>(RESP_Province.class) {
            @Override
            protected void onSuccess(RESP_Province obj) {
                DistrictModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(String error) {
                DistrictModel.this.onErrror(error);
            }
        });
    }

    @Override
    protected void exception(String message) {
        onErrror("UNKNOW Error");
    }

    protected abstract void onSuccess(RESP_Province province);

    protected abstract void onErrror(String err);
}
