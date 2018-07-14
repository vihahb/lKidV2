package com.snq.ikidz.teacher.view.activity.login

import com.snq.ikidz.teacher.model.RESP.RESP_GetSchoolByDistrict
import com.snq.ikidz.teacher.model.RESP.RESP_Login
import com.snq.ikidz.teacher.model.entity.ProvinceOrDistrict
import com.snq.ikidz.teacher.view.base.IBasicActivity

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