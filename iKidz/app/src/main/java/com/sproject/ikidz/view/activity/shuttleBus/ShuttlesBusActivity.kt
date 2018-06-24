package com.sproject.ikidz.view.activity.shuttleBus

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.ShuttleBusEntity
import com.sproject.ikidz.model.entity.ShuttlepickupPersonEntity
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_shuttles_bus.*
import java.util.*

class ShuttlesBusActivity : BaseActivity(), IShuttlesBus {
    override fun getShuttlesPersonSuccess(data: List<ShuttlepickupPersonEntity>) {
        dataPerson.clear()
        if (data.isNotEmpty()) {
            dataPerson.addAll(data)
            tv_message_dialog.visibility = View.INVISIBLE
        } else {
            tv_message_dialog.visibility = View.VISIBLE
            tv_message_dialog.text = this@ShuttlesBusActivity.resources.getString(R.string.mesage_no_data)
        }
        adapterPerson.notifyDataSetChanged()
        showPerson()
    }

    override fun getShuttlesPersonError(errorMessage: String) {
        showLongToast(errorMessage)
    }

    override fun getShuttleBusSuccess(data: MutableList<ShuttleBusEntity>) {
        tv_message.visibility = View.GONE
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun getShuttlesError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    lateinit var adapter: AdapterShuttlesBus
    lateinit var list: java.util.ArrayList<ShuttleBusEntity>
    lateinit var presenter: ShuttleBusPresenter

    lateinit var dialog: Dialog
    lateinit var dataPerson: ArrayList<ShuttlepickupPersonEntity>
    lateinit var adapterPerson: AdapterShuttlePerson
    lateinit var tv_message_dialog: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuttles_bus)
        initToolbar("THÔNG TIN ĐƯA ĐÓN", true)
        initView()
    }

    private fun initView() {
        initDialog()

        presenter = ShuttleBusPresenter(this)
        list = ArrayList()
        rcl_shuttles_bus.layoutManager = LinearLayoutManager(this)
        adapter = AdapterShuttlesBus(list, this, ItemClickListenerGeneric<ShuttleBusEntity> { id, data -> presenter.getShuttlePerson(data!!.studentId) })
        rcl_shuttles_bus.adapter = adapter
        presenter.getShuttleBus()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("NewApi")
    protected fun initDialog() {
        dataPerson = ArrayList()

        dialog = Dialog(this@ShuttlesBusActivity, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_layout_shuttle_bus)
//        Objects.requireNonNull<Window>(dialog.window).attributes.windowAnimations = R.style.DialogAnim
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        adapterPerson = AdapterShuttlePerson(dataPerson, this@ShuttlesBusActivity, ItemClickListenerGeneric<ShuttlepickupPersonEntity> { id, data -> })

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        tv_message_dialog = dialog.findViewById(R.id.tv_message)
        val rcl_shuttle_person = dialog.findViewById<RecyclerView>(R.id.rcl_shuttles_pickup)
        rcl_shuttle_person.layoutManager = LinearLayoutManager(this)
        rcl_shuttle_person.adapter = adapterPerson

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showPerson() {
        if (!dialog.isShowing)
            dialog.show()
    }
}
