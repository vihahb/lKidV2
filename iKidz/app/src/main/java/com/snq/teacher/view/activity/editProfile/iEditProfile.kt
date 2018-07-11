package com.snq.teacher.view.activity.editProfile

import com.snq.teacher.model.entity.DataUser

interface iEditProfile {
    fun updateProfileSuccess()

    fun getProfileSuccess(user: DataUser)
}