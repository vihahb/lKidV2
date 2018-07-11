package com.snq.teacher.presenter.editProfile

import com.snq.teacher.iKidApplications
import com.snq.teacher.model.database.GetObjectByKeyModel
import com.snq.teacher.model.entity.DataUser
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.SharedUtils
import com.snq.teacher.view.activity.editProfile.iEditProfile

class EditProfilePresenter(var view: iEditProfile) {

    var TAG = "EditProfilePresenter::class.java"

    init {
        getUser()
    }

    private fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onError(message: ErrorEntity?) {
                    iKidApplications.log(TAG, "getUser: " + message!!.errorMessage)
                }

                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        view.getProfileSuccess(`object`)
                    }
                }
            }
        }
    }

}