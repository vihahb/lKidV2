package com.sproject.ikidz.view.fragment.news;

import com.sproject.ikidz.model.RESP.RESP_DataNews;
import com.sproject.ikidz.model.RESP.RESP_like;
import com.sproject.ikidz.model.entity.ErrorEntity;
import com.sproject.ikidz.view.base.IBasicActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NewsInf extends IBasicActivity {
    void GetNewsSuccess(@NotNull RESP_DataNews news, int page);

    void GetNewsError(@NotNull String s);

    void likeSuccess(RESP_like data, int pos);

    void likeError(@Nullable ErrorEntity s);
}
