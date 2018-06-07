package com.sproject.ikidz.view.activity.login

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ProvinceOrDistrict
import com.sproject.ikidz.presenter.Login.LoginPresenter
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : BaseActivity(), ILogin {


    lateinit var provinceAdapter: AdapterSpinnerProvince
    lateinit var districtAdapter: AdapterSpinnerProvince
    private lateinit var provinceData: ArrayList<ProvinceOrDistrict>
    private lateinit var districtData: ArrayList<ProvinceOrDistrict>

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

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        provinceData = ArrayList()
        districtData = ArrayList()
        initView()

    }

    private fun initView() {
        initToolbar(R.id.toolbar, R.id.toolbar_text, "Đăng nhập", false)
        provinceAdapter = AdapterSpinnerProvince(this, provinceData)
        sp_province.adapter = provinceAdapter

        districtAdapter = AdapterSpinnerProvince(this, districtData)
        sp_district.adapter = districtAdapter

        sp_province.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (provinceAdapter.getItemId(position) > -1) {
                    presenter.GetDistrict((provinceAdapter.getItem(position) as ProvinceOrDistrict).id)
                }
            }

        }

        presenter.GetProvince()
    }
}
