package com.snq.teacher.view.activity.schoolProfile

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.snq.teacher.R
import com.snq.teacher.model.entity.ProfileEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.teacher.view.activity.schoolProfile.info.ProfileInfoActivity
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_school_profile.*
import java.io.Serializable
import java.util.*

class SchoolProfileActivity : BaseActivity(), ISchoolProfileActivity {
    override fun getProfileError(errorMessage: String) {
        showLongToast(errorMessage)
    }

    override fun getProfileSuccess(data: List<ProfileEntity>) {
        if (page == 1) {
            if (data.isNotEmpty()) {
                list.clear()
                list.addAll(data)
                tv_message.visibility = View.INVISIBLE
                page++
            } else {
                tv_message.visibility = View.VISIBLE
                tv_message.text = this@SchoolProfileActivity.resources.getString(R.string.mesage_no_data)
            }
        } else {
            tv_message.visibility = View.INVISIBLE
            list.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    lateinit var adapter: AdapterSchoolProfile
    lateinit var presenter: SchoolProfileActivityPresenter
    lateinit var list: ArrayList<ProfileEntity>

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_profile)
        initToolbar("NHẬN XÉT HỌC BẠ", true)
        initView()
    }

    private fun initView() {
        presenter = SchoolProfileActivityPresenter(this)
        list = ArrayList()
        rcl_profile.layoutManager = LinearLayoutManager(this)
        adapter = AdapterSchoolProfile(list, this, ItemClickListenerGeneric<ProfileEntity> { id, data ->
            var intent = Intent(this@SchoolProfileActivity, ProfileInfoActivity::class.java)
            intent.putExtra(Constants.OBJECT, data as Serializable)
            startActivity(intent)
        })
        rcl_profile.adapter = adapter
        presenter.getSchoolProfile(page)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
