package com.sproject.ikidz.view.base;

import android.app.Dialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sproject.ikidz.R;

import java.util.Objects;

public abstract class BaseFragment extends Fragment implements IBasicActivity{
    private Dialog progressDialog;
    @Override
    public void showLongToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

            progressDialog = new Dialog(getContext(), R.style.Theme_Transparent);
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
}
