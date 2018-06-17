package com.sproject.ikidz.presenter.Login

import com.sproject.ikidz.model.RESP.RESP_Basic
import com.sproject.ikidz.model.server.ResetPassword
import com.sproject.ikidz.view.activity.login.IResetView


/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:56 PM
 * Email: vihahb@gmail.com
 */
class ResetPresenter(var view: IResetView) {

    var TAG = "ResetPresenter"

    fun sendResetRequest(link_api: String, email: String){
        object : ResetPassword(link_api, email) {
            override fun onSuccess(basic: RESP_Basic) {
                view.showLongToast("Vui lòng kiểm tra email để thay đổi mật khẩu.")
            }

            override fun onError(s: String?) {
                view.showLongToast(s)
            }

        }
    }
}