package com.sproject.ikidz.view.fragment.news;

import com.sproject.ikidz.model.RESP.RESP_DataNews;

import org.jetbrains.annotations.NotNull;

public interface NewsInf {
    void GetNewsSuccess(@NotNull RESP_DataNews news, int page);

    void GetNewsError(@NotNull String s);
}
