package com.snq.ikidz.teacher.presenter.Login

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.RESP.RESP_Basic
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.server.ResetPassword
import com.snq.ikidz.teacher.view.activity.login.IResetView


/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:56 PM
 * Email: vihahb@gmail.com
 */
class ResetPresenter(var view: IResetView) {

    var TAG = "ResetPresenter"

    fun sendResetRequest(link_api: String, email: String) {
        object : ResetPassword(link_api, email) {
            override fun onSuccess(basic: RESP_Basic) {
                view.showLongToast("Vui lòng kiểm tra email để thay đổi mật khẩu.")
            }

            override fun onError(s: ErrorEntity) {
                if (s.errorCode == 26) {
                    view.showLongToast(view.activity.getString(R.string.error_email_invalid))
                } else if (s.errorCode == 25) {
                    view.showLongToast(view.activity.getString(R.string.error_email_not_exists))
                } else {
                    view.showLongToast(s.errorMessage)
                }
            }

        }
    }
}