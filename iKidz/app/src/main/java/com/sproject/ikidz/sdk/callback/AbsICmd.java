package com.sproject.ikidz.sdk.callback;

import android.util.Log;

public abstract class AbsICmd implements ICmd {

    private static final String TAG = "AbsICmd";

    @Override
    public void run() {
        try {
            invoke();
        } catch (Exception e) {
            Log.e(TAG, "run Exception: " + e.toString());
            exception(e.getMessage());
        }
    }

    protected abstract void invoke();

    protected abstract void exception(String message);
}