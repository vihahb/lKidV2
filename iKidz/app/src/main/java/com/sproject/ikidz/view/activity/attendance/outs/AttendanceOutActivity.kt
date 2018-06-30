package com.sproject.ikidz.view.activity.attendance.outs

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AttendanceOut
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.Utils.TimeUtils
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_attendance_out.*
import java.util.*

//
class AttendanceOutActivity : BaseActivity(), IAttendanceOutActivity, SearchView.OnQueryTextListener {
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
        list!!.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun getAttendanceOutError(s: String?) {
        showLongToast(s)
    }

    var presenter: AttendanceOutPresenter? = null
    var list: ArrayList<AttendanceOut>? = null
    lateinit var adapter: AdapterAttendanceOut
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_out)

        initToolbar("ĐIỂM DANH VỀ", true)
        list = ArrayList<AttendanceOut>()
        initView()

        presenter = AttendanceOutPresenter(this)
        presenter!!.getListAttendanceOut()
    }

    private fun initView() {
        adapter = AdapterAttendanceOut(list, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcl_attendanced_out.layoutManager = layoutManager
        rcl_attendanced_out.adapter = adapter
        initTime()
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
                    adapter.setAll(b)
                }
            }
        }
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
