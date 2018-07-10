package com.sproject.ikidz.view.fragment.message

import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.database.GetObjectByKeyModel
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils

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