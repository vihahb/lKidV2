package com.sproject.ikidz.view.activity.mbr

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.StudentsEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.activity.mbr.info.MbrInfoActivity
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mbr_list.*
import java.io.Serializable
import java.util.*

class MbrListActivity : BaseActivity(), IMbr {
    override fun getMrbSuccess(list: List<StudentsEntity>) {
        if (list.isNotEmpty()) {
            this.list.addAll(list)
            tv_message.visibility = View.INVISIBLE
        } else {
            tv_message.visibility = View.VISIBLE
            tv_message.text = this.resources.getString(R.string.mesage_no_data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun getMbrError(message: String) {
        showLongToast(message)
    }

    lateinit var adapter: AdapterMbr
    lateinit var list: java.util.ArrayList<StudentsEntity>
    lateinit var presenter: MbrPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbr_list)
        initToolbar("SỔ SỨC KHỎE", true)
        initView()
    }

    fun initView() {
        presenter = MbrPresenter(this)
        list = ArrayList()
        rcl_mbr.layoutManager = LinearLayoutManager(this)
        adapter = AdapterMbr(list, this, ItemClickListenerGeneric<StudentsEntity> { id, data ->
            var intent = Intent(this@MbrListActivity, MbrInfoActivity::class.java)
            intent.putExtra(Constants.OBJECT, data as Serializable)
            startActivity(intent)
        })
        rcl_mbr.adapter = adapter
        presenter.getMbr()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
