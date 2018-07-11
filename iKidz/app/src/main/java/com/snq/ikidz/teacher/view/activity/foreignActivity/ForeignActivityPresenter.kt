package com.snq.ikidz.teacher.view.activity.foreignActivity

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_ForeignData
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.GetForeignActivity
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils

class ForeignActivityPresenter(private var view: IForeignActivity) {

    var TAG = "ForeignActivityPresenter"

    fun getForeignActivity() {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)

        object : GetForeignActivity(token) {

            override fun onSuccess(respond: RESP_ForeignData) {
                iKidApplications.log(TAG, "getAbsentByClass success: " + respond.toString())
                view.getForeignSuccess(respond.data.data)
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "getAbsentByClass onError: " + s.errorMessage)
                view.getForeignError(s.errorMessage)
            }

        }
    }
}