package com.sproject.ikidz.model.server;

import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_Province;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 11:02 PM
 * Email: vihahb@gmail.com
 */
public abstract class ProvinceModel extends AbsICmd {
    private BasicModel basicModel = new BasicModel();

    public ProvinceModel() {
        run();
    }

    @Override
    protected void invoke() {
        String url = basicModel.MANAGER_LINK_API + basicModel.OLD_VERSION_API + basicModel.PROVINCE_API;
        iKidApplications.log("ProvinceModel", url);

        basicModel.requestServer.getApi(url, null, new ResponseHandle<RESP_Province>(RESP_Province.class) {
            @Override
            protected void onSuccess(RESP_Province obj) {
                ProvinceModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(String error) {
                ProvinceModel.this.onErrror(error);
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
