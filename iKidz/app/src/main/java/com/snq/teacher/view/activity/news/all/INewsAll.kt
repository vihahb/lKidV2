package com.snq.teacher.view.activity.news.all

import com.snq.teacher.model.entity.CommentEntity
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.entity.RespondCommentEntity
import com.snq.teacher.view.base.IBasicActivity

public interface INewsAll : IBasicActivity {
    fun GetCommentSuccess(data: List<CommentEntity>)
    fun GetCommentError(s: ErrorEntity?)
    fun commentError()
    fun commentSuccess(data: RespondCommentEntity?)
}