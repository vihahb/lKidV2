package com.sproject.ikidz.view.activity.learnActivity

import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.LearnActivityEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.ItemMoreActionGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_learn.*
import java.util.*


class LearnActivity : BaseActivity(), ILearnActivityView {
    override fun getLearnActivitySuccess(data: List<LearnActivityEntity>) {

        if (page == 1) {
            if (data.isEmpty()) {
                tv_message.visibility = View.GONE
                tv_message.text = resources.getString(R.string.mesage_no_data)
            } else {
                tv_message.visibility = View.GONE
                list.clear()
                list.addAll(data)
            }
        } else {
            tv_message.visibility = View.GONE
            list.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun getLearnActivityError(errorMessage: String) {
        tv_message.visibility = View.GONE
        tv_message.text = errorMessage
    }

    lateinit var list: ArrayList<LearnActivityEntity>
    lateinit var presenter: LearnActivityPresenter
    lateinit var adapter: AdapterLearnActivity
    var page = 1

    lateinit var tv_time: TextView
    lateinit var tv_morning: TextView
    lateinit var tv_afternoon: TextView

    lateinit var tv_title: TextView
    lateinit var edt_time: EditText
    lateinit var edt_morning: EditText
    lateinit var edt_afternoon: EditText
    lateinit var btnCloses: Button
    lateinit var btnAgrees: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        initToolbar("HOẠT ĐỘNG HỌC", true)
        presenter = LearnActivityPresenter(this)
        initView()
    }

    private fun initView() {
        initDialog()
        initDialogCreateOrUpdate()
        list = ArrayList()
        adapter = AdapterLearnActivity(list, this, object : ItemMoreActionGeneric<LearnActivityEntity> {
            override fun ItemClick(type: Int, id: Int, data: LearnActivityEntity) {
                when (type) {
                    Constants.TYPE_VIEW -> {
                        setDataView(data)
                    }
                    Constants.TYPE_EDIT -> {
                        showUpdateDialog(data)
                    }
                }
            }
        })

        rcl_learn_activity.layoutManager = LinearLayoutManager(this)
        rcl_learn_activity.adapter = adapter

        rcl_learn_activity.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab_add.visibility === View.VISIBLE) {
                    fab_add.hide()
                } else if (dy < 0 && fab_add.visibility !== View.VISIBLE) {
                    fab_add.show()
                }
            }
        })

        fab_add.setOnClickListener {
            //add a learn activity
            showAddLearnActivity()
        }
        presenter.getLearnActivity(page)
    }

    private lateinit var dialog: Dialog
    private lateinit var dialogAddOrUpdate: Dialog

    private fun initDialog() {

        dialog = Dialog(this@LearnActivity, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_view_learn_activity)
//        Objects.requireNonNull<Window>(dialog.window).attributes.windowAnimations = R.style.DialogAnim
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        tv_time = dialog.findViewById<TextView>(R.id.tv_time)
        tv_morning = dialog.findViewById<TextView>(R.id.tv_morning)
        tv_afternoon = dialog.findViewById<TextView>(R.id.tv_afternoon)
        val btnClose = dialog.findViewById<Button>(R.id.btnClose)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun initDialogCreateOrUpdate() {

        dialogAddOrUpdate = Dialog(this@LearnActivity, R.style.Theme_Transparent)
        dialogAddOrUpdate.setContentView(R.layout.dialog_add_learn_activity)
//        Objects.requireNonNull<Window>(dialog.window).attributes.windowAnimations = R.style.DialogAnim
        dialogAddOrUpdate.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialogAddOrUpdate.setCancelable(true)
        dialogAddOrUpdate.setCanceledOnTouchOutside(true)

        tv_title = dialogAddOrUpdate.findViewById<TextView>(R.id.tv_title)
        edt_time = dialogAddOrUpdate.findViewById<EditText>(R.id.edt_time)
        edt_morning = dialogAddOrUpdate.findViewById<EditText>(R.id.edt_morning)
        edt_afternoon = dialogAddOrUpdate.findViewById<EditText>(R.id.edt_afternoon)
        btnCloses = dialogAddOrUpdate.findViewById<Button>(R.id.btnClose)
        btnAgrees = dialogAddOrUpdate.findViewById<Button>(R.id.btnAgree)

        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day -> edt_time.setText("" + day + "-" + (month + 1) + "-" + year) }
        }

        btnCloses.setOnClickListener {
            dialogAddOrUpdate.dismiss()
        }
    }

    fun showUpdateDialog(entity: LearnActivityEntity) {
        tv_title.text = "Sửa hoạt động học"

        edt_time.setText(entity.activityDate)
        edt_morning.setText(entity.learnMorning)
        edt_afternoon.setText(entity.learnAfternoon)

        btnAgrees.setOnClickListener {
            if (validate()) {
                presenter.createOrUpdateLearn(Constants.TYPE_EDIT, entity.id, edt_morning.text.toString(), edt_afternoon.text.toString(), edt_time.text.toString())
            }
        }
        if (!dialogAddOrUpdate.isShowing)
            dialogAddOrUpdate.show()
    }

    private fun showAddLearnActivity() {
        btnAgrees.setOnClickListener {
            if (validate()) {
                //tạo hoat động học
                presenter.createOrUpdateLearn(Constants.TYPE_ADD, null, edt_morning.text.toString(), edt_afternoon.text.toString(), edt_time.text.toString())
            }
        }
        if (!dialogAddOrUpdate.isShowing)
            dialogAddOrUpdate.show()
    }

    private fun validate(): Boolean {
        if (edt_time.text.isEmpty()) {
            showLongToast("Vui lòng chọn thời gian!")
            return false
        }
        if (edt_morning.text.isEmpty()) {
            showLongToast("Vui lòng điền nội dung học buổi sáng!")
            return false
        }

        if (edt_afternoon.text.isEmpty()) {
            showLongToast("Vui lòng điền nội dung học buổi chiều!")
            return false
        }
        return true
    }

    fun setDataView(entity: LearnActivityEntity) {
        tv_time.text = entity.activityDate
        tv_morning.text = entity.learnMorning
        tv_afternoon.text = entity.learnAfternoon
        if (!dialog.isShowing)
            dialog.show()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

}
