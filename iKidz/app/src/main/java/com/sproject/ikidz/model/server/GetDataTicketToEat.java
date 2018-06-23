package com.sproject.ikidz.model.server;

import com.sproject.ikidz.model.BasicModel;
import com.sproject.ikidz.model.RESP.RESP_DataEatTicket;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.sdk.callback.AbsICmd;
import com.sproject.ikidz.sdk.callback.ResponseHandle;

public abstract class GetDataTicketToEat extends AbsICmd {

    int id;
    String link_api, token;
    BasicModel basicModel = new BasicModel();

    public GetDataTicketToEat(int id, String token, String link_api) {
        this.id = id;
        this.token = token;
        this.link_api = link_api;
        run();
    }

    @Override
    protected void invoke() {
        String url = link_api + "get-list-ticket-of-class";
        String json = "{\"class_id\":" + id + "}";
        basicModel.requestServer.postApi(url, json, token, new ResponseHandle<RESP_DataEatTicket>(RESP_DataEatTicket.class) {
            @Override
            protected void onSuccess(RESP_DataEatTicket obj) {
                GetDataTicketToEat.this.onSucces(obj);
            }

            @Override
            protected void onError(ErrorEntity error) {
                GetDataTicketToEat.this.onError(error);
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSucces(RESP_DataEatTicket data);

    protected abstract void onError(ErrorEntity s);
}