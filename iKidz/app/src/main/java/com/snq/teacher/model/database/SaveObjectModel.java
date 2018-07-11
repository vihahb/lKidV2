package com.snq.teacher.model.database;

import android.support.annotation.NonNull;

import com.snq.teacher.model.entity.ErrorEntity;
import com.snq.teacher.sdk.callback.AbsICmd;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Author: Lê Công Long Vũ
 * Date: 11/13/2017
 * Email: leconglongvu@gmail.com
 */
public abstract class SaveObjectModel<T extends RealmObject> extends AbsICmd {
    private Realm realm;

    private T object;

    public SaveObjectModel(T object) {
        this.object = object;

        initRealm();
        run();
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void invoke() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(object);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
                SaveObjectModel.this.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                realm.close();
                SaveObjectModel.this.onError(new ErrorEntity(-1, error.getMessage()));
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