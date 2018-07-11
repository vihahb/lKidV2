package com.snq.teacher.view.activity.learnOverTime.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.Html
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.snq.teacher.R
import com.snq.teacher.model.entity.LearnOverTimeEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.Utils.TimeUtils
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.teacher.sdk.callback.TimePickerListener
import com.snq.teacher.view.activity.learnOverTime.fragment.presenter.LearnOverTimeFragmentPre
import com.snq.teacher.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ot.*

class LearnOverTimeFragment : BaseFragment(), ILearnOverTimeFragment, SearchView.OnQueryTextListener {
    override fun updateTimePickSuccess(position: Int, timePickReturn: String) {
        showLongToast("Cập nhật thời gian trả trẻ thành công!")
        list[position].timePick = timePickReturn
        adapter.notifyItemChanged(position)
        if (dialogReturn.isShowing)
            dialogReturn.dismiss()
    }

    override fun updateTimePickError() {
        showLongToast("Cập nhật thời gian trả trẻ không thành công!")
        if (dialogReturn.isShowing)
            dialogReturn.dismiss()
    }

    override fun validOTError() {
        showLongToast("Xác nhận không thành công!")
        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun validOTSuccess(position: Int) {
        showLongToast("Xác nhận thành công!")
        list[position].received = "1"
        adapter.notifyItemChanged(position)
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

    override fun getMoreTimeError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    override fun getMoreTimeSuccess(data: List<LearnOverTimeEntity>) {
        if (data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = activity!!.resources.getString(R.string.mesage_no_data)
        }
        list.addAll(data)
        adapter.notifyDataSetChanged()


    }

    var type = -1
    var page = 1

    lateinit var presenter: LearnOverTimeFragmentPre
    lateinit var adapter: AdapterLearnOTFragment
    lateinit var list: ArrayList<LearnOverTimeEntity>
    private var searchView: SearchView? = null

    companion object {
        fun newInstance(type: Int): LearnOverTimeFragment {
            val args = Bundle()
            args.putInt(Constants.MORETIME_TYPE, type)
            val fragment = LearnOverTimeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(Constants.MORETIME_TYPE)

        presenter = LearnOverTimeFragmentPre(this)

        list = ArrayList()
        adapter = AdapterLearnOTFragment(list, context, object : ItemClickListenerGeneric<LearnOverTimeEntity> {
            override fun ItemClick(position: Int, data: LearnOverTimeEntity) {
                if (data.received == "0")
                    showDialogs(data, position, true)
                else {
                    if (data.timePick.isNullOrEmpty())
                        showValidReturnChid(data, position)
                    else
                        showDialogs(data, position, false)
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView()
    }

    private fun initView() {
        initDialog()
        initDialogReturn()
        rcl_learn.layoutManager = LinearLayoutManager(context)
        rcl_learn.adapter = adapter
        presenter.getMoreTimeByClass(page, type)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<LearnOverTimeEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullNameStudent.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
    }


    private lateinit var dialog: Dialog
    private lateinit var dialogReturn: Dialog

    lateinit var tv_title: TextView
    lateinit var tv_name: TextView
    lateinit var tv_date_start: TextView
    lateinit var tv_date_end: TextView
    lateinit var tv_content: TextView
    lateinit var tv_pick_time: TextView

    private lateinit var btnUpdate: Button

    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(context, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_ot)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_title = dialog.findViewById(R.id.tv_title)
        tv_name = dialog.findViewById(R.id.tv_name)
        tv_content = dialog.findViewById(R.id.tv_content)
        tv_date_start = dialog.findViewById(R.id.tv_date_start)
        tv_date_end = dialog.findViewById(R.id.tv_date_end)
        tv_pick_time = dialog.findViewById(R.id.tv_pick_time)

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnUpdate.text = "Xác nhận"
        tv_title.text = "Chi tiết xin học thêm giờ"
    }

    lateinit var edt_pickTime: EditText
    lateinit var tv_name_return: TextView
    lateinit var btnValid: Button
    lateinit var timePickReturn: String
    @SuppressLint("NewApi")
    private fun initDialogReturn() {
        timePickReturn = TimeUtils.getCurrentTimeFormat("HH:mm")

        dialogReturn = Dialog(context, R.style.Theme_Transparent)
        dialogReturn.setContentView(R.layout.dialog_return_child)
        dialogReturn.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialogReturn.setCancelable(true)
        dialogReturn.setCanceledOnTouchOutside(true)
        tv_name_return = dialogReturn.findViewById(R.id.tv_return_name)
        edt_pickTime = dialogReturn.findViewById(R.id.edt_time)

        val btnClose = dialogReturn.findViewById<Button>(R.id.btnClose)
        btnValid = dialogReturn.findViewById<Button>(R.id.btnValid)
        edt_pickTime.setText(timePickReturn)
        btnClose.setOnClickListener {
            dialogReturn.dismiss()
        }
        btnValid.text = "Xác nhận"
        edt_pickTime.setOnClickListener {
            TimeUtils.getInstance().showTimePickerDialog(context, object : TimePickerListener {
                override fun onTimeSelected(time: String?, dateTime: String?) {
                    edt_pickTime.setText(time!!)
                    timePickReturn = time
                }
            })
        }
    }

    fun showValidReturnChid(data: LearnOverTimeEntity, position: Int) {
        tv_name_return.text = data.fullNameStudent
        btnValid.setOnClickListener {
            presenter.updateTimePick(data.id, timePickReturn, position)
        }
        dialogReturn.show()
    }

    fun showDialogs(data: LearnOverTimeEntity, position: Int, notValid: Boolean) {
        var name = "<b>Học sinh: </b>" + data.fullNameStudent
        var start = "<b>Ngày bắt đầu: </b>" + data.startDay
        var end = "<b>Ngày Kết thúc: </b>" + data.endDay
        var content = "<b>Nội dung: </b>" + data.content

        var timePick = ""
        if (data.timePick.isNullOrEmpty())
            timePick = "<b>Thời gian đón trẻ: </b> Chưa có"
        else
            timePick = "<b>Thời gian đón trẻ: </b>" + data.timePick

        tv_name.text = Html.fromHtml(name)
        tv_date_start.text = Html.fromHtml(start)
        tv_date_end.text = Html.fromHtml(end)
        tv_content.text = Html.fromHtml(content)
        tv_pick_time.text = Html.fromHtml(timePick)

        if (notValid) {
            btnUpdate.visibility = View.VISIBLE
            btnUpdate.setOnClickListener {
                presenter.validOverTime(data, position)
            }
        } else
            btnUpdate.visibility = View.GONE


        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_attendance_out, menu)
        val itemSearch = menu!!.findItem(R.id.search_view_attendance_out)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }
}