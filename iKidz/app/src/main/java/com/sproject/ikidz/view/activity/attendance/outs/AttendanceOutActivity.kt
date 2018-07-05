package com.sproject.ikidz.view.activity.attendance.outs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AttendanceOut
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DialogClickListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_attendance_out.*
import java.util.*

//
class AttendanceOutActivity : BaseActivity(), IAttendanceOutActivity, SearchView.OnQueryTextListener {
    override fun updateAttendanceSuccess(pos: Int, checked: Int?) {
        if (pos > -1) {
            list!![pos].checked = 3
            adapter.notifyItemChanged(pos)
        } else {
            chk_check_all_out.isChecked = true
            list!!.forEachIndexed { index, _ ->
                list!![index].checked = 3
            }
            adapter.notifyItemRangeChanged(0, list!!.size)
        }

        showLongToast("Cập nhật thông tin điểm danh thành công!")
        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun updateAttendanceError() {
        showLongToast("Cập nhật thông tin điểm danh không thành công!")
        chk_check_all_out.isChecked = false
        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty())
            adapter.filterList(list)
        else
            filterItem(newText!!)
        return true
    }

    override fun getAttendanceOutSuccess(data: MutableList<AttendanceOut>) {
        if (data.size > 0) {
            if (list!!.isNotEmpty())
                list!!.clear()
            list!!.addAll(data)
            adapter.notifyDataSetChanged()
        } else{
            tv_message.visibility = View.VISIBLE
            tv_message.text = "Dữ liệu trống. Vui lòng thử lại sau!"
        }
    }

    override fun getAttendanceOutError(s: String?) {
        showLongToast(s)
    }

    var presenter: AttendanceOutPresenter? = null
    var list: ArrayList<AttendanceOut>? = null
    lateinit var adapter: AdapterAttendanceOut
    private var searchView: SearchView? = null
    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_out)
        presenter = AttendanceOutPresenter(this)
        initToolbar("ĐIỂM DANH VỀ", true)
        list = ArrayList()
        initView()
    }

    private fun initView() {
        initDialog()
        adapter = AdapterAttendanceOut(list, this, ItemClickListenerGeneric<AttendanceOut> { pos, data ->
            if (data!!.checked.toInt() == 3)
                showDialogs(pos, data, true)
            else
                showDialogs(pos, data, false)
        })
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcl_attendanced_out.layoutManager = layoutManager
        rcl_attendanced_out.adapter = adapter
        initTime()
    }

    private fun initTime() {
        date = TimeUtils.getCurrentTimeFormat("dd-MM-yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day ->
                date = "" + day + "-" + (month + 1) + "-" + year
                edt_time.setText(date)
                presenter!!.getListAttendanceOut(date)
            }
        }

        chk_check_all_out.setOnCheckedChangeListener { _, b ->
            run {
                if (list!!.size > 0) {
                    if (b) {
                        showMaterialDialog("Thông báo", "Bạn có muốn điểm danh về cho tất cả học sinh?", object : DialogClickListener {
                            override fun DesagreeListener() {
                                chk_check_all_out.isChecked = false
                            }

                            override fun AgreeListener() {
                                presenter!!.updateAllAttendanceOut(3, list!!, edt_time.text.toString())
                            }
                        })
                    }
                } else {
                    showLongToast("Dữ liệu trống. Vui lòng thử lại sau!")
                }

            }
        }

        presenter!!.getListAttendanceOut(date)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<AttendanceOut>()

        for (s in list!!) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullName.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
    }

    private lateinit var dialog: Dialog
    lateinit var tv_name: TextView
    lateinit var rdo_group_out: RadioGroup
    lateinit var rdo_chuave: RadioButton
    lateinit var rdo_dave: RadioButton
    private lateinit var btnUpdate: Button

    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(this, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_attendance_out)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_name = dialog.findViewById(R.id.tv_name)
        rdo_group_out = dialog.findViewById(R.id.rdo_group_in)
        rdo_chuave = dialog.findViewById(R.id.rdo_chuave)
        rdo_dave = dialog.findViewById(R.id.rdo_dave)

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnUpdate = dialog.findViewById(R.id.btnUpdate)

        rdo_group_out.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.rdo_chuave -> {
                    setRadioCorlor(2)
                }
                R.id.rdo_chuave -> {
                    setRadioCorlor(3)
                }
                else -> {
                    setRadioCorlor(-1)
                }
            }
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showDialogs(pos: Int, data: AttendanceOut, outed: Boolean) {
        setRadioCorlor(-1)
        tv_name.text = data.fullName
        if (outed) {
            setRadioCorlor(3)
            rdo_group_out.check(R.id.rdo_dave)
            btnUpdate.visibility = View.GONE
            rdo_chuave.isClickable = false
            rdo_dave.isClickable = false
        } else {
            setRadioCorlor(2)
            var type = 0
            rdo_group_out.check(R.id.rdo_chuave)
            btnUpdate.visibility = View.VISIBLE
            rdo_chuave.isClickable = true
            rdo_dave.isClickable = true
            btnUpdate.setOnClickListener {
                when (rdo_group_out.checkedRadioButtonId) {
                    R.id.rdo_chuave -> {
                        type = 2
                    }
                    R.id.rdo_dave -> {
                        type = 3
                    }
                }
                if (type > 2)
                    presenter!!.updateAttendanceOut(pos, 3, data, edt_time.text.toString())
                else {
                    showLongToast("Vui lòng chọn trạng thái đã về!")
                    return@setOnClickListener
                }
            }
        }
        dialog.show()
    }

    fun setRadioCorlor(type: Int) {
        when (type) {
            2 -> {
                rdo_chuave.setTextColor(this.resources.getColor(R.color.red))
                rdo_dave.setTextColor(this.resources.getColor(R.color.normal_text_dario))
            }
            3 -> {
                rdo_chuave.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_dave.setTextColor(this.resources.getColor(R.color.green_yet))
            }
            -1 -> {
                rdo_chuave.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_dave.setTextColor(this.resources.getColor(R.color.normal_text_dario))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_attendance_out, menu!!)
        val itemSearch = menu.findItem(R.id.search_view_attendance_out)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
