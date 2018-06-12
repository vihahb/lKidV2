package com.sproject.ikidz.presenter.Login

import com.sproject.ikidz.R
import com.sproject.ikidz.iKidApplications
import com.sproject.ikidz.model.RESP.RESP_GetSchoolByDistrict
import com.sproject.ikidz.model.RESP.RESP_Login
import com.sproject.ikidz.model.RESP.RESP_Province
import com.sproject.ikidz.model.database.SaveObjectModel
import com.sproject.ikidz.model.entity.DataUser
import com.sproject.ikidz.model.entity.UserLogin
import com.sproject.ikidz.model.server.DistrictModel
import com.sproject.ikidz.model.server.GetSchoolByDistrictModel
import com.sproject.ikidz.model.server.LoginModel
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

    fun getProvince() {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : ProvinceModel() {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getProvincesSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: String?) {
                iKidApplications.log(TAG, err!!)
                view.getProvincesError()
                view.closeProgressBar()
            }
        }
    }

    fun getDistrict(codeProvince: Int) {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : DistrictModel(codeProvince) {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getDistrictSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: String) {
                iKidApplications.log(TAG, err)
                view.getDistrictError()
                view.closeProgressBar()
            }
        }
    }

    fun getSchoolByDistrict(distrcId: Int) {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : GetSchoolByDistrictModel(distrcId) {
            override fun onSuccess(schools: RESP_GetSchoolByDistrict) {
                iKidApplications.log(TAG, JsonHelper.toJson(schools))
                view.getSchoolByDistrictSuccess(schools)
                view.closeProgressBar()
            }

            override fun onErrror(err: String) {
                iKidApplications.log(TAG, err)
                view.getSchoolsError(err)
                view.closeProgressBar()
            }

        }
    }

    fun onLogin(username: String, pass: String, link_api: String) {
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_login_data))
        var login = UserLogin(username, pass)
        object: LoginModel(login, link_api){
            override fun onSuccess(login: RESP_Login) {
                iKidApplications.log(TAG, JsonHelper.toJson(login))
                view.onLoginSuccess(login)
                view.closeProgressBar()
            }

            override fun onError(error: String?) {
                iKidApplications.log(TAG, error)
                view.onLoginError(error)
                view.closeProgressBar()
            }

        }
    }

    fun saveUserInfo(data: DataUser) {
        object : SaveObjectModel<DataUser> (data){
            override fun onSuccess() {
                iKidApplications.log(TAG, "saveUserInfo success!")
                view.onSaveUserSuccess()
            }

            override fun onError() {
                iKidApplications.log(TAG, "saveUserInfo success!")
                view.onSaveUserError()
            }

        }
    }
}
