package com.sproject.ikidz.view.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ravikoradiya.library.CenterTitle;
import com.ravikoradiya.library.CenterTitleKt;
import com.sproject.ikidz.R;
import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.sdk.Utils.DisplayUtils;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity implements IBasicActivity {

    boolean isWaitingForExit = false;
    private Dialog progressDialog;
    Toolbar toolbar;

    public void initToolbar(int id, String title, boolean showBack) {
        toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);
        CenterTitle.centerTitle(toolbar, true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayShowHomeEnabled(true);
        if (showBack) {
//            toolbar.setPadding(0, 0, DisplayUtils.DpToPx(64, BaseActivity.this), 0);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationIcon(R.drawable.ic_action_back);

        if (title != null)
            toolbar.setTitle(title);
    }

    public void setTitleToolbar(String title) {
        if (title != null)
            toolbar.setTitle(title);
    }

    /*
     * Hiển thị tiến trình (đang thực hiện)
     * */
    public void showProgressBar(boolean isTouchOutside, boolean isCancel, String message) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }

            progressDialog = new Dialog(this, R.style.Theme_Transparent);
            progressDialog.setContentView(R.layout.dialog_progressbar);
            Objects.requireNonNull(progressDialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnim;
            //noinspection ConstantConditions
            progressDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            progressDialog.setCancelable(isTouchOutside);
            progressDialog.setCanceledOnTouchOutside(isCancel);

            if (message != null) {
                TextView textView = progressDialog.findViewById(R.id.txt_message);
                textView.setText(message);
            }

            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * Kết thúc hiển thị tiến trình (đang thực hiện)
     * */
    public void closeProgressBar() {
        if (progressDialog != null) {
            new Handler().postDelayed(() -> progressDialog.dismiss(), 1000);
        }
    }


    @Override
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showConfirmExitApp() {
        if (isWaitingForExit) {
            finish();
        } else {
            new WaitingForExit().execute();
        }
    }

    protected void startActivityAndFinish(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_2_left);
    }
protected void startActivity(Class clazz, String key, Object object, String key1, Object object1) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        intent.putExtra(key1, (Serializable) object1);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_2_left);
    }

    protected void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_2_left);
    }

    protected void startActivityForResult(Class clazz, String key, Object object, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_2_left);
    }

    class WaitingForExit extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isWaitingForExit = true;
            showLongToast(getString(R.string.message_back_press_to_exit));
        }

        @Override
        protected Object doInBackground(Object... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            isWaitingForExit = false;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_2_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_2_right);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
