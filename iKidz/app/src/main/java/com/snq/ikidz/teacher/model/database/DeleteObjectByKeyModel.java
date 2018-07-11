package com.snq.ikidz.teacher.model.database;

import android.support.annotation.NonNull;

import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.sdk.callback.AbsICmd;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Author: Lê Công Long Vũ
 * Date: 11/13/2017
 * Email: leconglongvu@gmail.com
 */
public abstract class DeleteObjectByKeyModel<T extends RealmObject> extends AbsICmd {
    private Realm realm;

    private Class<T> tClass;
    private String key;
    private int value;

    public DeleteObjectByKeyModel(Class<T> tClass, String key, int value) {
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

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm bgRealm) {
                bgRealm.where(tClass).equalTo(key, value).findFirst().deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
                DeleteObjectByKeyModel.this.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                realm.close();
                DeleteObjectByKeyModel.this.onError(new ErrorEntity(-1, error.getMessage()));
            }
        });
    }

    @Override
    protected void exception(ErrorEntity message) {
        onError(message);
    }

    protected abstract void onSuccess();

    protected abstract void onError(ErrorEntity message);
}