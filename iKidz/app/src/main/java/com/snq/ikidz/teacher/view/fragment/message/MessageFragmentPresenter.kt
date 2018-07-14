package com.snq.ikidz.teacher.view.fragment.message

import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.database.GetObjectByKeyModel
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class MessageFragmentPresenter(private var view: IMessageView) {
    var TAG = "MessageFragmentPresenter"

    fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onError(message: ErrorEntity?) {
                    iKidApplications.log(TAG, "getUser error: " + message!!.errorMessage)
                }

                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        view.getUsserSuccess(`object`)
                    }
                }
            }
        }
    }
}