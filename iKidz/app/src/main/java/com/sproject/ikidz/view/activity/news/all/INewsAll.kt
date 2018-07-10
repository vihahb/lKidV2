package com.sproject.ikidz.view.activity.news.all

import com.sproject.ikidz.model.entity.CommentEntity
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.RespondCommentEntity
import com.sproject.ikidz.view.base.IBasicActivity

public interface INewsAll : IBasicActivity {
    fun GetCommentSuccess(data: List<CommentEntity>)
    fun GetCommentError(s: ErrorEntity?)
    fun commentError()
    fun commentSuccess(data: RespondCommentEntity?)
}