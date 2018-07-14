package com.snq.ikidz.teacher.view.activity.commentDay

import com.snq.ikidz.teacher.model.entity.CommentLearnEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ICommentLearn : IBasicActivity {
    fun getCommentLearnData(data: List<CommentLearnEntity>)
    fun getCommentLearnDataError(message: String)
}