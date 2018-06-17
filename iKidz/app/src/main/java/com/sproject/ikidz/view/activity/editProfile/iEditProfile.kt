package com.sproject.ikidz.view.activity.editProfile

import com.sproject.ikidz.model.entity.DataUser

interface iEditProfile {
    fun updateProfileSuccess()

    fun getProfileSuccess(user: DataUser)
}