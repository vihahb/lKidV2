package com.snq.ikidz.teacher.presenter.Login

import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.iKidApplications
import com.snq.ikidz.teacher.model.RESP.RESP_GetSchoolByDistrict
import com.snq.ikidz.teacher.model.RESP.RESP_Login
import com.snq.ikidz.teacher.model.RESP.RESP_Province
import com.snq.ikidz.teacher.model.database.SaveObjectModel
import com.snq.ikidz.teacher.model.entity.DataUser
import com.snq.ikidz.teacher.model.entity.ErrorEntity
import com.snq.ikidz.teacher.model.entity.UserLogin
import com.snq.ikidz.teacher.model.server.DistrictModel
import com.snq.ikidz.teacher.model.server.GetSchoolByDistrictModel
import com.snq.ikidz.teacher.model.server.LoginModel
import com.snq.ikidz.teacher.model.server.ProvinceModel
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.JsonHelper
import com.snq.ikidz.teacher.sdk.Utils.NetworkUtils
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.view.activity.login.ILogin

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
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : ProvinceModel() {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getProvincesSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: ErrorEntity?) {
                iKidApplications.log(TAG, err!!.errorMessage)
                view.getProvincesError()
                view.closeProgressBar()
            }
        }
    }

    fun getDistrict(codeProvince: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : DistrictModel(codeProvince) {
            override fun onSuccess(province: RESP_Province?) {
                iKidApplications.log(TAG, JsonHelper.toJson(province!!))
                view.getDistrictSuccess(province.data)
                view.closeProgressBar()
            }

            override fun onErrror(err: ErrorEntity) {
                iKidApplications.log(TAG, err.errorMessage)
                view.getDistrictError()
                view.closeProgressBar()
            }
        }
    }

    fun getSchoolByDistrict(distrcId: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_loading_data))
        object : GetSchoolByDistrictModel(distrcId) {
            override fun onSuccess(schools: RESP_GetSchoolByDistrict) {
                iKidApplications.log(TAG, JsonHelper.toJson(schools))
                view.getSchoolByDistrictSuccess(schools)
                view.closeProgressBar()
            }

            override fun onErrror(err: ErrorEntity) {
                iKidApplications.log(TAG, err.errorMessage)
                view.getSchoolsError(err.errorMessage)
                view.closeProgressBar()
            }

        }
    }

    fun onLogin(username: String, pass: String, link_api: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        view.showProgressBar(false, true, view.activity.getString(R.string.mesage_login_data))
        var login = UserLogin(username, pass)
        object : LoginModel(login, link_api) {
            override fun onSuccess(login: RESP_Login) {
                iKidApplications.log(TAG, JsonHelper.toJson(login))
                view.onLoginSuccess(login)
                view.closeProgressBar()
            }

            override fun onError(error: ErrorEntity) {
                iKidApplications.log(TAG, error.errorMessage)
                view.onLoginError(error.errorMessage)
                view.closeProgressBar()
            }
        }
    }

    fun saveUserInfo(data: DataUser) {
        if (data.user.roles.slug == Constants.TEACHER) {
            SharedUtils.getInstance().putBooleanValue(Constants.TEACHER, true)
        }

        object : SaveObjectModel<DataUser>(data) {
            override fun onSuccess() {
                SharedUtils.getInstance().putStringValue(Constants.BASE_URL, data.user.base_url)
                iKidApplications.log(TAG, "saveUserInfo success!")
                view.onSaveUserSuccess()
            }

            override fun onError(s: ErrorEntity) {
                iKidApplications.log(TAG, "saveUserInfo error: " + s.errorMessage)
                view.onSaveUserError()
            }

        }
    }
}
