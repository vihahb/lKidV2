package com.snq.ikidz.teacher.view.activity.school.info

import com.snq.ikidz.teacher.model.entity.*
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISchoolNewsInfo : IBasicActivity {
    fun getSchoolInfoSuccess(data: NewsInfo)
    fun getSchoolInfoError(errorMessage: String)
    fun getProfileSuccess(dataUser: DataUser)
    fun GetCommentSuccess(data: List<CommentEntity>)
    fun GetCommentError(s: ErrorEntity?)
    fun commentSuccess(data: RespondCommentEntity?)
    fun commentError()
}