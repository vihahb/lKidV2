package com.sproject.ikidz.view.activity.attendance.`in`

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
import com.sproject.ikidz.model.entity.AttendanceIn
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.sdk.callback.DialogClickListener
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_attendance_in.*
import java.util.*

class AttendanceInActivity : BaseActivity(), IAttendanceIn, SearchView.OnQueryTextListener {
    override fun updateAttendanceSuccess(pos: Int, checked: Int?) {
        if (pos > -1) {
            list!![pos].checked = checked
            adapter!!.notifyItemChanged(pos)
        } else {
            chk_check_all_out.isChecked = true
            list!!.forEachIndexed { index, _ ->
                list!![index].checked = checked
            }
            adapter!!.notifyItemRangeChanged(0, list!!.size)
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
            adapter!!.filterList(list)
        else
            filterItem(newText!!)
        return true
    }

    override fun getAttendanceInSuccess(data: List<AttendanceIn>) {
        if (data.size > 0) {
            if (list!!.isNotEmpty())
                list!!.clear()
            list!!.addAll(data)
            adapter!!.notifyDataSetChanged()
        } else{
            tv_message.visibility = View.VISIBLE
            tv_message.text = "Dữ liệu trống. Vui lòng thử lại sau!"
        }
    }

    override fun getAttendanceInError(s: String?) {
        showLongToast(s)
    }

    var adapter: AdapterAttendanceIn? = null
    var presenter: AttendanceInPresenter? = null
    var list: ArrayList<AttendanceIn>? = null
    private var searchView: SearchView? = null
    var date = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_in)
        initToolbar("ĐIỂM DANH", true)
        presenter = AttendanceInPresenter(this)
        list = ArrayList<AttendanceIn>()
        initView()


    }

    private fun initView() {
        initDialog()
        adapter = AdapterAttendanceIn(list, this, object : ItemClickListenerGeneric<AttendanceIn> {
            override fun ItemClick(position: Int, data: AttendanceIn) {
                if (data.checked != null)
                    showDialogs(position, data, data.checked)
                else
                    showDialogs(position, data, -1)
            }
        })
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcl_attendanced_in.layoutManager = layoutManager
        rcl_attendanced_in.adapter = adapter
        initTime()
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<AttendanceIn>()

        for (s in list!!) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullName.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter!!.filterList(filterdNames)
    }

    private fun initTime() {
        date = TimeUtils.getCurrentTimeFormat("dd-MM-yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day ->
                date = "" + day + "-" + (month + 1) + "-" + year
                edt_time.setText(date)
                presenter!!.getListAttendanceIn(date)
            }
        }

        chk_check_all_out.setOnCheckedChangeListener { _, b ->
            run {
                if (list!!.size > 0) {
                    if (b) {
                        showMaterialDialog("Thông báo", "Bạn có muốn điểm danh đến lớp cho tất cả học sinh?", object : DialogClickListener {
                            override fun DesagreeListener() {
                                chk_check_all_out.isChecked = false
                            }

                            override fun AgreeListener() {
                                presenter!!.updateAllAttendanceOut(2, list!!, edt_time.text.toString())
                            }
                        })
                    }
                } else{
                    showLongToast("Dữ liệu trống. Vui lòng thử lại sau!")
                }
            }
        }

        presenter!!.getListAttendanceIn(date)
    }

    private lateinit var dialog: Dialog
    lateinit var tv_name: TextView
    lateinit var rdo_group_in: RadioGroup
    lateinit var rdo_cophep: RadioButton
    lateinit var rdo_khongphep: RadioButton
    lateinit var rdo_denlop: RadioButton
    private lateinit var btnUpdate: Button

    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(this, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_attendances_in)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_name = dialog.findViewById(R.id.tv_name)
        rdo_group_in = dialog.findViewById(R.id.rdo_group_in)
        rdo_cophep = dialog.findViewById(R.id.rdo_cophep)
        rdo_khongphep = dialog.findViewById(R.id.rdo_khongphep)
        rdo_denlop = dialog.findViewById(R.id.rdo_denlop)

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnUpdate = dialog.findViewById(R.id.btnUpdate)

        rdo_group_in.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.rdo_khongphep -> {
                    setRadioCorlor(0)
                }
                R.id.rdo_cophep -> {
                    setRadioCorlor(1)
                }
                R.id.rdo_denlop -> {
                    setRadioCorlor(2)
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

    fun showDialogs(pos: Int, data: AttendanceIn, state: Int) {
        setRadioCorlor(-1)
        var type = state

        /**
         * state = 0: Nghi khong phep
         * state = 1: Nghi co phep
         * state = 2: Den lop
         * */
        when (state) {
            0 -> {
                setRadioCorlor(0)
                rdo_group_in.check(R.id.rdo_khongphep)
                btnUpdate.visibility = View.GONE
            }
            1 -> {
                setRadioCorlor(1)
                rdo_group_in.check(R.id.rdo_cophep)
                btnUpdate.visibility = View.GONE
            }
            2 -> {
                setRadioCorlor(2)
                rdo_group_in.check(R.id.rdo_denlop)
                btnUpdate.visibility = View.GONE
            }
            else -> {
                setRadioCorlor(-1)
                rdo_group_in.clearCheck()
                btnUpdate.visibility = View.VISIBLE
            }
        }

        if (data.checked != null && data.checked < 3) {
            rdo_cophep.isClickable = false
            rdo_khongphep.isClickable = false
            rdo_denlop.isClickable = false
        } else {
            rdo_cophep.isClickable = true
            rdo_khongphep.isClickable = true
            rdo_denlop.isClickable = true
        }
        tv_name.text = data.fullName
        btnUpdate.setOnClickListener {
            type = when (rdo_group_in.checkedRadioButtonId) {
                R.id.rdo_khongphep -> {
                    0
                }
                R.id.rdo_cophep -> {
                    1
                }
                R.id.rdo_denlop -> {
                    2
                }
                else -> {
                    3
                }
            }
            if (type < 3)
                presenter!!.updateAttendanceInt(pos, type, data, edt_time.text.toString())
            else {
                showLongToast("Vui lòng chọn trạng thái điểm danh!")
                return@setOnClickListener
            }
        }
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_attendance_out, menu)
        val itemSearch = menu!!.findItem(R.id.search_view_attendance_out)
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

    fun setRadioCorlor(type: Int) {
        when (type) {
            0 -> {
                rdo_khongphep.setTextColor(this.resources.getColor(R.color.red))
                rdo_cophep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_denlop.setTextColor(this.resources.getColor(R.color.normal_text_dario))
            }
            1 -> {
                rdo_khongphep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_cophep.setTextColor(this.resources.getColor(R.color.yellow))
                rdo_denlop.setTextColor(this.resources.getColor(R.color.normal_text_dario))
            }
            2 -> {
                rdo_khongphep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_cophep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_denlop.setTextColor(this.resources.getColor(R.color.green_yet))
            }
            -1 -> {
                rdo_khongphep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_cophep.setTextColor(this.resources.getColor(R.color.normal_text_dario))
                rdo_denlop.setTextColor(this.resources.getColor(R.color.normal_text_dario))
            }
        }
    }
}
