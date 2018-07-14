package com.snq.ikidz.teacher.sdk.callback;

import com.snq.ikidz.teacher.model.entity.AlbumEntity;

import java.util.List;

public interface ImageItemClick {
    void onClickItem(List<AlbumEntity> data, int position);
}
