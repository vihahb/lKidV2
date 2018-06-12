package com.sproject.ikidz.view.activity.login

import com.sproject.ikidz.model.RESP.RESP_GetSchoolByDistrict
import com.sproject.ikidz.model.RESP.RESP_Login
import com.sproject.ikidz.model.entity.ProvinceOrDistrict
import com.sproject.ikidz.view.base.IBasicActivity

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 10:58 PM
 * Email: vihahb@gmail.com
 */
interface ILogin : IBasicActivity {
    fun getProvincesSuccess(data: List<ProvinceOrDistrict>)
    fun getDistrictSuccess(data: List<ProvinceOrDistrict>)
    fun getDistrictError()
    fun getProvincesError()
    fun getSchoolByDistrictSuccess(schools: RESP_GetSchoolByDistrict)
    fun getSchoolsError(err: String)
    fun onLoginError(error: String?)
    fun onLoginSuccess(login: RESP_Login)
    fun onSaveUserSuccess()
    fun onSaveUserError()
}