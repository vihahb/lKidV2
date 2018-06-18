package com.sproject.ikidz.view.activity.attendance.`in`

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AttendanceIn
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_attendance_in.*
import java.util.*

class AttendanceInActivity : BaseActivity(), IAttendanceIn {
    override fun getAttendanceInSuccess(data: List<AttendanceIn>) {
        list!!.addAll(data)
        adapter!!.notifyDataSetChanged()
    }

    override fun getAttendanceInError(s: String?) {
        showLongToast(s)
    }

    var adapter: AdapterAttendanceIn? = null
    var presenter: AttendanceInPresenter? = null
    var list: ArrayList<AttendanceIn>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_in)
        initToolbar("ĐIỂM DANH", true)
        list = ArrayList<AttendanceIn>()
        initView()
        presenter = AttendanceInPresenter(this)
        presenter!!.getListAttendanceIn()

    }

    private fun initView() {
        adapter = AdapterAttendanceIn(list, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcl_attendanced_in.layoutManager = layoutManager
        rcl_attendanced_in.adapter = adapter

        edt_filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filterItem(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty())
                    adapter!!.filterList(list)
            }
        })
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
        var cal = Calendar.getInstance()
        edt_time.setText("" + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR))
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day -> edt_time.setText("" + day + "/" + (month + 1) + "/" + year) }
        }

        chk_check_all_out.setOnCheckedChangeListener { _, b ->
            run {
                if (b) {
                    adapter!!.setAll(b)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
