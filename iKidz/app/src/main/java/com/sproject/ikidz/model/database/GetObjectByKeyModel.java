package com.sproject.ikidz.model.database;

import com.sproject.ikidz.sdk.callback.AbsICmd;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Author: Lê Công Long Vũ
 * Date: 11/13/2017
 * Email: leconglongvu@gmail.com
 */
public abstract class GetObjectByKeyModel<T extends RealmObject> extends AbsICmd {
    private Realm realm;

    private Class<T> tClass;
    private String key;
    private String value;

    public GetObjectByKeyModel(Class<T> tClass, String key, String value) {
        this.tClass = tClass;
        this.key = key;
        this.value = value;

        initRealm();
        run();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void invoke() {
        T object = realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findFirst());
        realm.close();

        onSuccess(object);
    }

    @Override
    protected void exception(String message) {
        onSuccess(null);
    }

    protected abstract void onSuccess(T object);
}