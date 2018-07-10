package com.sproject.ikidz.view.activity.school.info

import com.sproject.ikidz.model.entity.*
import com.sproject.ikidz.view.base.IBasicActivity

interface ISchoolNewsInfo : IBasicActivity {
    fun getSchoolInfoSuccess(data: NewsInfo)
    fun getSchoolInfoError(errorMessage: String)
    fun getProfileSuccess(dataUser: DataUser)
    fun GetCommentSuccess(data: List<CommentEntity>)
    fun GetCommentError(s: ErrorEntity?)
    fun commentSuccess(data: RespondCommentEntity?)
    fun commentError()
}