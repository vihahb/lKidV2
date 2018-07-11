package com.snq.ikidz.teacher.view.fragment.news;

import com.snq.ikidz.teacher.model.RESP.RESP_DataNews;
import com.snq.ikidz.teacher.model.RESP.RESP_like;
import com.snq.ikidz.teacher.model.entity.ErrorEntity;
import com.snq.ikidz.teacher.view.base.IBasicActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NewsInf extends IBasicActivity {
    void GetNewsSuccess(@NotNull RESP_DataNews news, int page);

    void GetNewsError(@NotNull String s);

    void likeSuccess(RESP_like data, int pos);

    void likeError(@Nullable ErrorEntity s);
}
