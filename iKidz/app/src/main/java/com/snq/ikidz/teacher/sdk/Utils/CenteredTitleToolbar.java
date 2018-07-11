package com.snq.ikidz.teacher.sdk.Utils;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.snq.ikidz.teacher.R;

import static android.content.Context.WINDOW_SERVICE;

public class CenteredTitleToolbar extends Toolbar {

    Context context;
    private TextView _titleTextView;
    private int _screenWidth;
    private boolean _centerTitle = true;

    public CenteredTitleToolbar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CenteredTitleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CenteredTitleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        _screenWidth = Math.round(displayMetrics.widthPixels / displayMetrics.density);

        _titleTextView = new TextView(getContext());
        _titleTextView.setTextAppearance(getContext(), R.style.ToolbarTitleText);
        _titleTextView.setText("Title");
        addView(_titleTextView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (_centerTitle) {
            int[] location = new int[2];
            _titleTextView.getLocationOnScreen(location);
            _titleTextView.setTranslationX(_titleTextView.getTranslationX() + (-location[0] + _screenWidth / 2 - _titleTextView.getWidth() / 2));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        _titleTextView.setText(title);
        requestLayout();
    }

    @Override
    public void setTitle(int titleRes) {
        _titleTextView.setText(titleRes);
        requestLayout();
    }

    public void setTitleCentered(boolean centered) {
        _centerTitle = centered;
        requestLayout();
    }

    private Point getScreenSize() {
        WindowManager wm = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        return screenSize;
    }
}