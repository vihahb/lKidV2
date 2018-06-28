package com.sproject.ikidz.view.activity.commentDay

import com.sproject.ikidz.model.entity.CommentLearnEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface ICommentLearn : IBasicActivity {
    fun getCommentLearnData(data: List<CommentLearnEntity>)
    fun getCommentLearnDataError(message: String)
}