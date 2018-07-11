package com.snq.teacher.view.activity.commentDay

import com.snq.teacher.model.entity.CommentLearnEntity
import com.snq.teacher.view.base.IBasicActivity

interface ICommentLearn : IBasicActivity {
    fun getCommentLearnData(data: List<CommentLearnEntity>)
    fun getCommentLearnDataError(message: String)
}