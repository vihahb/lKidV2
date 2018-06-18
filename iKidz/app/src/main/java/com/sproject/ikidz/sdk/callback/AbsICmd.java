package com.sproject.ikidz.sdk.callback;

import android.util.Log;

import com.sproject.ikidz.model.entity.ErrorEntity;

public abstract class AbsICmd implements ICmd {

    private static final String TAG = "AbsICmd";

    @Override
    public void run() {
        try {
            invoke();
        } catch (Exception e) {
            Log.e(TAG, "run Exception: " + e.toString());
            exception(new ErrorEntity(-1, e.getMessage()));
        }
    }

    protected abstract void invoke();

    protected abstract void exception(ErrorEntity message);
}