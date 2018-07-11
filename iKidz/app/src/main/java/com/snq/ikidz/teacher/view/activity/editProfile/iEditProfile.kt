package com.snq.ikidz.teacher.view.activity.editProfile

import com.snq.ikidz.teacher.model.entity.DataUser

interface iEditProfile {
    fun updateProfileSuccess()

    fun getProfileSuccess(user: DataUser)
}