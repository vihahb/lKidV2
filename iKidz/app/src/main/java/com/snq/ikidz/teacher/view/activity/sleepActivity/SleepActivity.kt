package com.snq.ikidz.teacher.view.activity.sleepActivity

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.SleepEntity
import com.snq.ikidz.teacher.model.entity.ValueSleepEntity
import com.snq.ikidz.teacher.sdk.Utils.TextUtils
import com.snq.ikidz.teacher.sdk.Utils.TimeUtils
import com.snq.ikidz.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sleep.*
import java.util.*

class SleepActivity : BaseActivity(), ISleepActivity, SearchView.OnQueryTextListener {
    override fun updateChildSuccess(pos: Int, childValue: ValueSleepEntity) {
//        list[pos].note = childValue.note
//        list[pos].startTime = TimeUtils.formatDate(childValue.startTime, "HH:mm", "HH:mm:ss")
//        list[pos].endTime = TimeUtils.formatDate(childValue.endTime, "HH:mm", "HH:mm:ss")
//
//        adapter.notifyItemChanged(pos)
        presenter.getDataSleep(date)
        showLongToast("Cập nhật thông tin ngủ của trẻ thành công!")
    }

    override fun updateChildError() {
        showLongToast("Cập nhật thông tin ngủ của trẻ không thành công!")
    }

    override fun updateTimeSleepSuccess() {
        list.forEachIndexed { index, sleepEntity ->
            list[index].startTime = start_time
            list[index].endTime = end_time
        }
        adapter.notifyItemRangeChanged(0, list.size)
        showLongToast("Cập nhật thời gian ngủ thành công!")
    }

    override fun updateTimeSleepError() {
        showLongToast("Cập nhật thời gian ngủ không thành công!")
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

    override fun getSleepData(data: List<SleepEntity>) {
        if (data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = resources.getString(R.string.mesage_no_data)
        } else {
            tv_message.visibility = View.INVISIBLE
            if (list.isNotEmpty())
                list.clear()
            list.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun getSleepDataError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    lateinit var presenter: SleepActivityPresenter
    lateinit var list: ArrayList<SleepEntity>
    lateinit var adapter: AdapterSleep
    lateinit var date: String
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)
        initToolbar("HOẠT ĐỘNG NGỦ", true)
        presenter = SleepActivityPresenter(this)
        initView()
    }

    private fun initView() {
        initDialog()
        initDialogEdit()
        date = TimeUtils.getCurrentTimeFormat("dd/MM/yyyy")
        edt_time.setText(date)
        edt_time.setOnClickListener {
            TimeUtils.getInstance().showDatePickerDialog(this) { year, month, day ->
                date = "" + day + "/" + (month + 1) + "/" + year
                edt_time.setText(date)
                presenter.getDataSleep(date)
            }
        }
        list = ArrayList()
        adapter = AdapterSleep(list, this, object : ItemClickListenerGeneric<SleepEntity> {
            override fun ItemClick(pos: Int, data: SleepEntity) {
                showDialogEsit(pos, data)
            }
        })
        rcl_sleep.layoutManager = LinearLayoutManager(this)
        rcl_sleep.adapter = adapter

        img_update_sleep_time.setOnClickListener {
            showDialogs()
        }

        presenter.getDataSleep(date)
    }


    private lateinit var dialog: Dialog
    private lateinit var edt_start: EditText
    private lateinit var edt_end: EditText
    private lateinit var btnAgree: Button
    var start_time = "12:00"
    var end_time = "14:00"
    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(this, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_set_time_sleep)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        edt_start = dialog.findViewById(R.id.edt_start)
        edt_end = dialog.findViewById(R.id.edt_end)
        btnAgree = dialog.findViewById(R.id.btnAgree)

        edt_start.setText(start_time)
        edt_end.setText(end_time)

        edt_start.setOnClickListener {
            TimeUtils.getInstance().showTimePickerDialog(this) { time, dateTime ->
                start_time = time
                edt_start.setText(time)
            }
        }

        edt_end.setOnClickListener {
            TimeUtils.getInstance().showTimePickerDialog(this) { time, dateTime ->
                end_time = time
                edt_end.setText(time)
            }
        }
    }

    fun showDialogs() {
        btnAgree.setOnClickListener {
            presenter.updateTimeSleep(start_time, end_time)
            dialog.dismiss()
        }
        if (!dialog.isShowing)
            dialog.show()
    }


    lateinit var dialogs: Dialog
    lateinit var edt_time_start: EditText
    lateinit var edt_time_end: EditText
    lateinit var edt_note: EditText
    lateinit var tv_user_name: TextView
    lateinit var btnUpdate: Button
    lateinit var btnClose: Button

    var child_start = ""
    var child_end = ""

    private fun initDialogEdit() {
        dialogs = Dialog(this, R.style.Theme_Transparent)
        dialogs.setContentView(R.layout.dialog_update_child_sleep)
        dialogs.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialogs.setCancelable(true)
        dialogs.setCanceledOnTouchOutside(true)

        tv_user_name = dialogs.findViewById(R.id.tv_name)
        edt_time_start = dialogs.findViewById(R.id.edt_time_start)
        edt_time_end = dialogs.findViewById(R.id.edt_time_end)
        edt_note = dialogs.findViewById(R.id.edt_note)
        btnUpdate = dialogs.findViewById(R.id.btnUpdate)
        btnClose = dialogs.findViewById(R.id.btnClose)

        edt_time_start.setOnClickListener {
            TimeUtils.getInstance().showTimePickerDialog(this) { time, dateTime ->
                child_start = time
                edt_time_start.setText(time)
            }
        }

        edt_time_end.setOnClickListener {
            TimeUtils.getInstance().showTimePickerDialog(this) { time, dateTime ->
                child_end = time
                edt_time_end.setText(time)
            }
        }

        btnClose.setOnClickListener {
            dialogs.dismiss()
        }
    }

    fun showDialogEsit(pos: Int, data: SleepEntity) {
        edt_time_start.setText(data.startTime)
        edt_time_end.setText(data.endTime)

        if (data.fullName != null)
            tv_user_name.text = data.fullName

        if (data.note != null)
            edt_note.setText(data.note)

        btnUpdate.setOnClickListener {
            if (edt_note.text.isNullOrEmpty()) {
                showLongToast("Vui lòng nhập nội dung ghi chú!")
                return@setOnClickListener
            }
            if (edt_time_start.text.isNullOrEmpty()) {
                showLongToast("Vui lòng chọn giờ bắt đầu!")
                return@setOnClickListener
            }
            if (edt_time_end.text.isNullOrEmpty()) {
                showLongToast("Vui lòng chọn giờ kết thúc!")
                return@setOnClickListener
            }

            var childValue = ValueSleepEntity(
                    edt_time_start.text.toString(),
                    edt_time_end.text.toString(),
                    data.fullName,
                    edt_note.text.toString(),
                    data.studentId
            )

            presenter.updateChildSleep(edt_time.text.toString(),
                    childValue, pos)

            dialogs.dismiss()
        }

        dialogs.show()
    }


    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<SleepEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullName.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
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
}
