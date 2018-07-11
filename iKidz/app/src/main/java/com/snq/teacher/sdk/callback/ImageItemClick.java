package com.snq.teacher.sdk.callback;

import com.snq.teacher.model.entity.AlbumEntity;

import java.util.List;

public interface ImageItemClick {
    void onClickItem(List<AlbumEntity> data, int position);
}
