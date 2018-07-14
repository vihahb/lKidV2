package com.snq.ikidz.teacher.model.server;

import com.snq.ikidz.teacher.iKidApplications;
import com.snq.ikidz.teacher.model.BasicModel;
import com.snq.ikidz.teacher.model.RESP.RESP_Province;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;
import com.snq.ikidz.teacher.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/7/2018
 * Time: 12:01 AM
 * Email: vihahb@gmail.com
 */
public abstract class DistrictModel extends AbsICmd {
    int pid;
    private BasicModel basicModel = new BasicModel();

    public DistrictModel(int pid) {
        this.pid = pid;
        run();
    }

    @Override
    protected void invoke() {
        String url = basicModel.MANAGER_LINK_API + basicModel.VERSION_API + basicModel.DISTRICT_API;
        iKidApplications.log("DistrictModel", url);
        String json = "{\"pid\":" + pid + "}";
        iKidApplications.log("DistrictModel", json);
        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_Province>(RESP_Province.class) {
            @Override
            protected void onSuccess(RESP_Province obj) {
                DistrictModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                DistrictModel.this.onErrror(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onErrror(message);
    }

    protected abstract void onSuccess(RESP_Province province);

    protected abstract void onErrror(ErrorEntity err);
}
