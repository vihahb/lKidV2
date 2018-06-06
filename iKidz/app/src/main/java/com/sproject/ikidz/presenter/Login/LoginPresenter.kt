package com.sproject.ikidz.presenter.Login

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_Province
import com.sproject.ikidz.model.server.DistrictModel
import com.sproject.ikidz.model.server.ProvinceModel
import com.sproject.ikidz.sdk.Utils.JsonHelper
import com.sproject.ikidz.view.activity.login.ILogin

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:56 PM
 * Email: vihahb@gmail.com
 */
class LoginPresenter(private val view: ILogin) {
    var TAG = "LoginPresenter"

    fun GetProvince() {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : ProvinceModel() {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getProvincesSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: String?) {
                iKidApplications.log(TAG, err!!)
                view.closeProgressBar()
            }
        }
    }

    fun GetDistrict(codeProvince: Int) {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : DistrictModel(codeProvince) {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getDistrictSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: String?) {
                iKidApplications.log(TAG, err!!)
                view.closeProgressBar()
            }
        }
    }
}
