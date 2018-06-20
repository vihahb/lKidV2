package com.sproject.ikidz.view.activity.foreignActivity

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_ForeignData
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.server.GetForeignActivity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils

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