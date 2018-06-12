package com.sproject.ikidz.presenter

import com.sproject.ikidz.model.database.GetObjectByKeyModel
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.activity.home.IHomeView

import java.util.ArrayList

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:57 PM
 * Email: vihahb@gmail.com
 */
class HomePresenter(private val view: IHomeView) {

    fun getUser() {
        val token = SharedUtils.getInstance().getStringValue(Constants.TOKEN)
        if (token != null) {
            object : GetObjectByKeyModel<DataUser>(DataUser::class.java, "token", token) {
                override fun onSuccess(`object`: DataUser?) {
                    if (`object` != null) {
                        view.setDataUser(`object`)
                    }
                }
            }
        }
    }
}
