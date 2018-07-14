package com.snq.ikidz.teacher.view.activity.news.all

import com.snq.ikidz.teacher.model.entity.CommentEntity
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.RespondCommentEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

public interface INewsAll : IBasicActivity {
    fun GetCommentSuccess(data: List<CommentEntity>)
    fun GetCommentError(s: ErrorEntity?)
    fun commentError()
    fun commentSuccess(data: RespondCommentEntity?)
}