package com.sproject.ikidz.view.fragment.news.showImage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sproject.ikidz.R;
import com.sproject.ikidz.iKidApplications;
import com.sproject.ikidz.model.entity.AlbumEntity;
import com.sproject.ikidz.sdk.Utils.WidgetUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class PageImageFragment extends Fragment {
    private static final String TAG = "PageImageFragment";
    private AlbumEntity url;

    public static PageImageFragment getInstance(AlbumEntity url) {
        PageImageFragment f = new PageImageFragment();
        Bundle args = new Bundle();
        args.putSerializable("image_entity", (Serializable) url);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = (AlbumEntity) getArguments().getSerializable("image_entity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image);

        Picasso.with(iKidApplications.context)
                .load(url.getImage())
                .noPlaceholder()
                .error(R.drawable.ic_load_image_error)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        iKidApplications.log(TAG, "Load image success url: " + url.toString());
                    }

                    @Override
                    public void onError() {
                        iKidApplications.log(TAG, "Load image error url: " + url.toString());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}