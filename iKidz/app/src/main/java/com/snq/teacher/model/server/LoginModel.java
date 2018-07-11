package com.snq.teacher.model.server;

import com.snq.teacher.model.BasicModel;
import com.snq.teacher.model.RESP.RESP_Login;
import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.model.entity.UserLogin;
import com.snq.teacher.sdk.Utils.JsonHelper;
import com.snq.teacher.sdk.callback.AbsICmd;
import com.snq.teacher.sdk.callback.ResponseHandle;

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/9/2018
 * Time: 12:04 AM
 * Email: vihahb@gmail.com
 */
public abstract class LoginModel extends AbsICmd {

    UserLogin userLogin;
    BasicModel basicModel = new BasicModel();
    String link_api;

    public LoginModel(UserLogin userLogin, String link_api) {
        this.userLogin = userLogin;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + basicModel.LOGIN_API;
        String json = JsonHelper.toJson(userLogin);
        basicModel.requestServer.postApi(url, json, new ResponseHandle<RESP_Login>(RESP_Login.class) {
            @Override
            protected void onSuccess(RESP_Login obj) {
                LoginModel.this.onSuccess(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                LoginModel.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess(RESP_Login login);

    protected abstract void onError(ErrorEntity error);
}
