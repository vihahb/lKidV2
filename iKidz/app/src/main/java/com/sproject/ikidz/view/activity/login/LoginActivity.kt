package com.sproject.ikidz.view.activity.login

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_GetSchoolByDistrict
import com.sproject.ikidz.model.RESP.RESP_Login
import com.sproject.ikidz.model.entity.ProvinceOrDistrict
import com.sproject.ikidz.model.entity.SchoolByDistrict
import com.sproject.ikidz.presenter.Login.LoginPresenter
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.Base64Helper
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.view.activity.home.HomeActivity
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : BaseActivity(), ILogin {

    private var rememberLogin = false
    lateinit var provinceAdapter: AdapterSpinnerProvince
    lateinit var districtAdapter: AdapterSpinnerDistrict
    lateinit var schoolByDistrictAdapter: AdapterSpinnerSchoolByDistrict
    private lateinit var provinceData: ArrayList<ProvinceOrDistrict>
    private lateinit var districtData: ArrayList<ProvinceOrDistrict>
    private lateinit var schoolByDistrictData: ArrayList<SchoolByDistrict>
    private lateinit var link_api: String
    private lateinit var presenter: LoginPresenter


    override fun onSaveUserSuccess() {
        showLongToast(resources.getString(R.string.message_login_success))
        startActivityAndFinish(HomeActivity::class.java)
    }

    override fun onSaveUserError() {
        showLongToast(resources.getString(R.string.message_login_success))
        startActivityAndFinish(HomeActivity::class.java)
    }

    override fun onLoginError(error: String?) {
        showLongToast(error)
    }

    override fun onLoginSuccess(login: RESP_Login) {
        SharedUtils.getInstance().putStringValue(Constants.CURRENT_TOKEN, login.data.token)
        SharedUtils.getInstance().putStringValue(Constants.LINK_API, link_api)
        SharedUtils.getInstance().putStringValue(Constants.AVATAR_USER, login.data.user.avatar)
        SharedUtils.getInstance().putStringValue(Constants.FIREBASE_TOKEN, login.data.user.tokenFirebase)
        presenter.saveUserInfo(login.data)
//        if (rememberLogin) {
//            SharedUtils.getInstance().putStringValue(Constants.CURRENT_TOKEN, login.data.token)
//            SharedUtils.getInstance().putStringValue(Constants.LINK_API, link_api)
//            presenter.saveUserInfo(login.data)
//        } else {
//            showLongToast(resources.getString(R.string.message_login_success))
//            startActivityAndFinish(HomeActivity::class.java)
//        }
    }

    override fun getDistrictSuccess(data: List<ProvinceOrDistrict>) {
        if (districtData.size > 0)
            districtData.clear()

        districtData.addAll(data)
        districtData.add(0, ProvinceOrDistrict(-1, this.resources.getString(R.string.action_select_district)))
        districtAdapter.notifyDataSetChanged()
    }

    override fun getProvincesSuccess(data: List<ProvinceOrDistrict>) {
        if (provinceData.size > 0)
            provinceData.clear()

        provinceData.addAll(data)
        provinceData.add(0, ProvinceOrDistrict(-1, this.resources.getString(R.string.action_select_province)))
        provinceAdapter.notifyDataSetChanged()
    }

    override fun getSchoolByDistrictSuccess(schools: RESP_GetSchoolByDistrict) {
        if (schoolByDistrictData.size > 0) {
            schoolByDistrictData.clear()
        }

        if (schools.data.size == 0) {
            schools.data.add(0, SchoolByDistrict(-1, this.resources.getString(R.string.action_select_non_data_school)))
        }
        schoolByDistrictData.addAll(schools.data)
//        schoolByDistrictData.add(0, SchoolByDistrict(-1, this.resources.getString(R.string.action_select_school)))
        schoolByDistrictAdapter.notifyDataSetChanged()
    }

    override fun getSchoolsError(err: String) {
        schoolByDistrictData.add(0, SchoolByDistrict(-1, this.resources.getString(R.string.action_select_non_data_school)))
        districtAdapter.notifyDataSetChanged()
    }

    override fun getProvincesError() {
        provinceData.add(0, ProvinceOrDistrict(-1, this.resources.getString(R.string.action_select_non_data_province)))
        provinceAdapter.notifyDataSetChanged()
    }

    override fun getDistrictError() {
        districtData.add(0, ProvinceOrDistrict(-1, this.resources.getString(R.string.action_select_non_data_district)))
        districtAdapter.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        provinceData = ArrayList()
        districtData = ArrayList()
        districtData.add(0, ProvinceOrDistrict(-1, this.resources.getString(R.string.action_select_non_data_district)))
        schoolByDistrictData = ArrayList()
        schoolByDistrictData.add(0, SchoolByDistrict(-1, this.resources.getString(R.string.action_select_non_data_school)))
        initView()

    }

    private fun initView() {
        initForm()
        initToolbar("ĐĂNG NHẬP", false)
        provinceAdapter = AdapterSpinnerProvince(this, provinceData)
        sp_province.adapter = provinceAdapter

        districtAdapter = AdapterSpinnerDistrict(this, districtData)
        sp_district.adapter = districtAdapter

        schoolByDistrictAdapter = AdapterSpinnerSchoolByDistrict(this, schoolByDistrictData)
        sp_school.adapter = schoolByDistrictAdapter

        sp_province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (provinceAdapter.getItemId(position) > -1) {
                    presenter.getDistrict((provinceAdapter.getItem(position) as ProvinceOrDistrict).id)
                }
            }

        }

        sp_district.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (districtAdapter.getItemId(position) > -1) {
                    presenter.getSchoolByDistrict((districtAdapter.getItem(position) as ProvinceOrDistrict).id)
                }
            }
        }

        sp_school.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (schoolByDistrictAdapter.getItemId(position) > -1) {
                    link_api = (schoolByDistrictAdapter.getItem(position) as SchoolByDistrict).linkApi
                }
            }

        }

        presenter.getProvince()

        btnLogin.setOnClickListener { onLogin() }
        tv_reset.setOnClickListener {
            if (validate()) {
                startActivity(ResetPasswordActivity::class.java, Constants.LINK_API, link_api)
            }
        }
    }

    private fun validate(): Boolean {
        if ((provinceAdapter.getItem(sp_province.selectedItemPosition) as ProvinceOrDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_province))
            return false
        }

        if ((districtAdapter.getItem(sp_district.selectedItemPosition) as ProvinceOrDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_district))
            return false
        }
        if ((schoolByDistrictAdapter.getItem(sp_school.selectedItemPosition) as SchoolByDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_school))
            return false
        }
        if (TextUtils.isEmpty(link_api)) {
            showLongToast(resources.getString(R.string.validate_field_school))
            return false
        }

        return true
    }

    fun onLogin() {
        if (!NetworkUtils.isConnected(activity)) {
            showLongToast(activity.resources.getString(R.string.error_network))
            return
        }
        if ((provinceAdapter.getItem(sp_province.selectedItemPosition) as ProvinceOrDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_province))
            return
        }

        if ((districtAdapter.getItem(sp_district.selectedItemPosition) as ProvinceOrDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_district))
            return
        }
        if ((schoolByDistrictAdapter.getItem(sp_school.selectedItemPosition) as SchoolByDistrict).id == -1) {
            showLongToast(resources.getString(R.string.validate_field_school))
            return
        }
        if (TextUtils.isEmpty(link_api)) {
            showLongToast(resources.getString(R.string.validate_field_school))
            return
        }

        if (edtPassword.text.toString().length < 6) {
            showLongToast(resources.getString(R.string.validate_field_password_too_shot))
            return
        }
        if (!TextUtils.isEmpty(edtUserName.text.toString()) && !TextUtils.isEmpty(edtPassword.text.toString())) {
//            if (chk_remember.isChecked) {
////                var key = Base64Helper.getEncode(edtPassword.text.toString())
////                SharedUtils.getInstance().putStringValue(Constants.USER_NAME, edtUserName.text.toString())
////                SharedUtils.getInstance().putStringValue(Constants.USER_PASS, key)
//                rememberLogin = true;
//            }
            var schoolCode = (schoolByDistrictAdapter.getItem(sp_school.selectedItemPosition) as SchoolByDistrict).codeSchool
            SharedUtils.getInstance().putStringValue(Constants.SCHOOL_CODE, schoolCode)
            presenter.onLogin(edtUserName.text.toString(), edtPassword.text.toString(), link_api)
        } else {
            showLongToast(resources.getString(R.string.validate_field_user_name_pass))
            return
        }
    }

    private fun initForm() {
        if (!TextUtils.isEmpty(SharedUtils.getInstance().getStringValue(Constants.USER_NAME)))
            edtUserName.setText(SharedUtils.getInstance().getStringValue(Constants.USER_NAME))

        if (!TextUtils.isEmpty(SharedUtils.getInstance().getStringValue(Constants.USER_PASS))) {
            var key = Base64Helper.getEncode(SharedUtils.getInstance().getStringValue(Constants.USER_PASS))
            edtPassword.setText(key)
        }
    }
}


