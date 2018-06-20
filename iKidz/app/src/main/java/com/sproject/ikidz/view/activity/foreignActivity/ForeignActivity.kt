package com.sproject.ikidz.view.activity.foreignActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ForeignActivityEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.callback.ItemClickListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.activity.foreignActivity.foreignInfo.ForeignInfoActivity
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_other_foreign.*
import java.io.Serializable
import java.util.*

class ForeignActivity : BaseActivity(), IForeignActivity {
    override fun getForeignSuccess(list: List<ForeignActivityEntity>) {
        if (list.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = activity!!.resources.getString(R.string.mesage_no_data)
        }
        this.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun getForeignError(message: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = message
    }

    private lateinit var presenter: ForeignActivityPresenter
    private lateinit var list: ArrayList<ForeignActivityEntity>
    private lateinit var adapter: AdapterForeignActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_foreign)
        initView()
    }

    private fun initView() {
        initToolbar("HOẠT ĐỘNG NGOẠI KHÓA", true)
        presenter = ForeignActivityPresenter(this)
        list = ArrayList()
        adapter = AdapterForeignActivity(list, this, object : ItemClickListenerGeneric<ForeignActivityEntity> {
            override fun ItemClick(id: Int, data: ForeignActivityEntity?) {
                var intent = Intent(this@ForeignActivity, ForeignInfoActivity::class.java)
                intent.putExtra(Constants.OBJECT, data as Serializable)
                startActivity(intent)
            }

        })
        rcl_foreign.layoutManager = LinearLayoutManager(this)
        rcl_foreign.adapter = adapter
        presenter.getForeignActivity()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
