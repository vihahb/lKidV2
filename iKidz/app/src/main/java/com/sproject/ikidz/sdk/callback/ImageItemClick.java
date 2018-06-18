package com.sproject.ikidz.sdk.callback;

import com.sproject.ikidz.model.entity.AlbumEntity;

import java.util.List;

public interface ImageItemClick {
    void onClickItem(List<AlbumEntity> data, int position);
}
