package com.snq.ikidz.teacher.sdk.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.snq.ikidz.teacher.iKidApplications;
import com.snq.ikidz.teacher.sdk.Commons.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

/**
 * Created by Vũ Hà Vi on 11/4/2016
 */
public class WidgetUtils {
    private static final String TAG = "WidgetUtils";

    /**
     * Image
     */
    public static void setImageURL(ImageView view, final String url, int resource) {
        if (TextUtils.isEmpty(url)) {
            if (resource > -1)
                view.setImageResource(resource);
            return;
        } else {
            String urls = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + "/" + url;
            Picasso.with(iKidApplications.context)
                    .load(urls)
                    .noPlaceholder()
                    .error(resource)
                    .fit()
                    .centerCrop()
                    .into(view);
        }
    }

    public static void getBitmapFromURL(final String url, Target target) {
        Picasso.with(iKidApplications.context)
                .load(url)
                .into(target);
    }

    public static void setImageUrlFull(ImageView view, final String url, int resource) {
        if (TextUtils.isEmpty(url)) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(url)
                .noPlaceholder()
                .error(resource)
                .into(view);
    }

    public static void setImageUrlFull(ImageView view, final String url, int resource, Callback callback) {
        if (TextUtils.isEmpty(url)) {
            view.setImageResource(resource);
            callback.onError();
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(url)
                .noPlaceholder()
                .error(resource)
                .into(view, callback);
    }

    public static void setImageURL(ImageView view, final String url, int resource, Callback callback) {
        if (TextUtils.isEmpty(url)) {
            view.setImageResource(resource);
            callback.onError();
            return;
        }

        String urls = SharedUtils.getInstance().getStringValue(Constants.BASE_URL) + url;

        iKidApplications.log(TAG, "url = " + url);

        Picasso.with(iKidApplications.context)
                .load(urls)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view, callback);
    }

    public static void setImageNotBaseURL(ImageView view, final String url, int resource, Callback callback) {
        if (TextUtils.isEmpty(url)) {
            view.setImageResource(resource);
            callback.onError();
            return;
        }

        iKidApplications.log(TAG, "url = " + url);

        Picasso.with(iKidApplications.context)
                .load(url)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view, callback);
    }

    public static void setImageFile(ImageView view, final File file, int resource) {
        if (file == null) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view);
    }

    public static void setImageFileFull(ImageView view, final File file, int resource) {
        if (file == null) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .into(view);
    }

    public static void setImageFileFull(ImageView view, final File file, int resource, Callback callback) {
        if (file == null) {
            view.setImageResource(resource);
            callback.onError();
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .into(view, callback);
    }

    public static void setImageFile(ImageView view, final File file, int resource, Callback callback) {
        if (file == null) {
            view.setImageResource(resource);
            callback.onError();
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view, callback);
    }

    public static void setImageFileAndDelete(ImageView view, final File file, int resource) {
        if (file == null) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view);
    }

    public static void setSmallImageFile(ImageView view, final File file, int resource) {
        if (file == null) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view);
    }

    public static void setSmallImageFile(ImageView view, String filePath, int resource) {
        if (TextUtils.isEmpty(filePath)) {
            view.setImageResource(resource);
            return;
        }

        File file = new File(filePath);

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view);
    }

    public static void setSmallImageFileAndDelete(ImageView view, final File file, int resource) {
        if (file == null) {
            view.setImageResource(resource);
            return;
        }

        Picasso.with(iKidApplications.context)
                .load(file)
                .noPlaceholder()
                .error(resource)
                .fit()
                .centerCrop()
                .into(view);
    }

    public static void setImageFileBlur(ImageView view, File file, int resource) {
        Bitmap bitmap;

        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
        } else {
            bitmap = BitmapFactory.decodeResource(iKidApplications.context.getResources(), resource);
        }

        view.setImageBitmap(BlurUtils.blur(iKidApplications.context, bitmap));
    }

    public static void setImageFileBlur(ImageView view, ImageView view2, int resource) {
        Bitmap bitmap;

        try {
            bitmap = ((BitmapDrawable) view2.getDrawable()).getBitmap();
        } catch (Exception e) {
            bitmap = BitmapFactory.decodeResource(iKidApplications.context.getResources(), resource);
        }

        view.setImageBitmap(BlurUtils.blur(iKidApplications.context, bitmap));
    }
    /**
     * End Image
     * */


    /**
     * Edittext
     */
    public static void setText(EditText editText, int content) {
        editText.setText(String.valueOf(content));
    }

    public static void setText(EditText editText, String title, int content) {
        editText.setText((title + content));
    }

    public static void setText(EditText editText, String content) {
        editText.setText(content);
    }

    public static void setText(EditText editText, String title, String content) {
        editText.setText((title + content));
    }


    public static void setTextWithResult(EditText editText, String content, String result) {
        if (TextUtils.isEmpty(content))
            editText.setHint(result);
        else
            editText.setText(content);
    }

    public static void setTextWithResult(EditText editText, String title, String content, String result) {
        if (TextUtils.isEmpty(content))
            editText.setHint(result);
        else
            editText.setText((title + content));
    }


    public static void setEditTextDrawable(EditText editText, int position, int resource) {
        switch (position) {
            case 0:
                editText.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
                break;
            case 1:
                editText.setCompoundDrawablesWithIntrinsicBounds(0, resource, 0, 0);
                break;
            case 2:
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, resource, 0);
                break;
            case 3:
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resource);
                break;
            default:
                break;
        }
    }

    public static void setEditTextDrawable(EditText editText, int position, Drawable resource) {
        switch (position) {
            case 0:
                editText.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                break;
            case 1:
                editText.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                break;
            case 2:
                editText.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                break;
            case 3:
                editText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
                break;
            default:
                break;
        }
    }
    /**
     * End Edittext
     * */


    /**
     * TextView
     */
    public static void setText(TextView view, int content) {
        view.setText(String.valueOf(content));
    }

    public static void setText(TextView view, String content) {
        view.setText(content);
    }

    public static void setText(TextView view, String title, String content) {
        view.setText((title + content));
    }

    public static void setTextWithResult(TextView view, String content, String result) {
        if (TextUtils.isEmpty(content))
            view.setText(result);
        else
            view.setText(content);
    }

    public static void setTextWithResult(TextView view, String title, String content, String result) {
        if (TextUtils.isEmpty(content))
            view.setText((title + result));
        else
            view.setText((title + content));
    }

    public static void setTextViewDrawable(TextView view, int position, int resource) {
        switch (position) {
            case 0:
                view.setCompoundDrawablesWithIntrinsicBounds(resource, 0, 0, 0);
                break;
            case 1:
                view.setCompoundDrawablesWithIntrinsicBounds(0, resource, 0, 0);
                break;
            case 2:
                view.setCompoundDrawablesWithIntrinsicBounds(0, 0, resource, 0);
                break;
            case 3:
                view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resource);
                break;
            default:
                break;
        }
    }

    public static void setTextViewDrawable(TextView view, int position, Drawable resource) {
        switch (position) {
            case 0:
                view.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                break;
            case 1:
                view.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                break;
            case 2:
                view.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                break;
            case 3:
                view.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
                break;
            default:
                break;
        }
    }

    /**
     * Scroll ẩn view
     *
     * @param view   view muốn ẩn
     * @param height quãng đường muốn scroll. 0 là về vị trí ban đầu
     */
    public static void slideView(View view, int height) {
        view.clearAnimation();
        view.animate().translationY(height).setDuration(200);
    }

    public static void setBackgroundResource(View view, int resource) {
        view.setBackgroundResource(resource);
    }

    public static void setBackgroundColor(View view, int resource) {
        view.setBackgroundColor(resource);
    }

    public static void setBackgroundDrawable(View view, int resource) {
        //noinspection deprecation
        view.setBackgroundDrawable(iKidApplications.context.getResources().getDrawable(resource));
    }


    private static boolean deleteFile(File file) {
        try {
            return file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
