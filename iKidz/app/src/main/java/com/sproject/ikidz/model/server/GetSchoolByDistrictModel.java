package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Basic;
import com.sproject.ikidz.model.RESP.RESP_GetSchoolByDistrict;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/8/2018
 * Time: 11:57 PM
 * Email: vihahb@gmail.com
 */
public abstract class GetSchoolByDistrictModel extends AbsICmd {

    BasicModel basicModel = new BasicModel();

    int d_id;

    public GetSchoolByDistrictModel(int d_id) {
        this.d_id = d_id;
        run();
    }

    @Override
    protected void invoke() {
        String url = basicModel.MANAGER_LINK_API + basicModel.VERSION_API + basicModel.GET_SCHOOL_BY_DISTRICT;
        String json = "{\"d_id\":" + d_id + "}";
        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_GetSchoolByDistrict>(RESP_GetSchoolByDistrict.class) {
            @Override
            protected void onSuccess(RESP_GetSchoolByDistrict obj) {
                GetSchoolByDistrictModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(String error) {
                GetSchoolByDistrictModel.this.onErrror(error);
            }
        });
    }

    @Override
    protected void exception(String message) {
        onErrror("UNKNOW Error");
    }

    protected abstract void onSuccess(RESP_GetSchoolByDistrict province);

    protected abstract void onErrror(String err);
}
