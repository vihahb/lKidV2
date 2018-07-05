package com.sproject.ikidz.view.activity.absent.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.text.Html
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AbsentEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.callback.ItemClickListenerGeneric
import com.sproject.ikidz.view.activity.absent.presenter.AbsentFragmentPre
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_absent.*
import java.util.*

class AbsentFragment : BaseFragment(), IAbsentFragment, SearchView.OnQueryTextListener {
    override fun validAbsenSuccess(position: Int, state: Int) {
        if (state == 1)
            showLongToast("Đã xác nhận đơn xin nghỉ!")
        else
            showLongToast("Đã từ chối xác nhận đơn xin nghỉ!")

        list[position].received = state
        adapter.notifyItemChanged(position)
        if (dialog.isShowing)
            dialog.dismiss()

        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun validAbsenError() {
        showLongToast("Xác nhận đơn xin nghỉ không thành công!")
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

    private fun filterItem(newText: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<AbsentEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullNameStudent.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(newText.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
    }

    override fun absentError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    override fun getAbsentSuccess(data: List<AbsentEntity>) {
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    var type = -1
    var page = 1

    lateinit var presenter: AbsentFragmentPre
    lateinit var adapter: AdapterAbsentFragment
    lateinit var list: ArrayList<AbsentEntity>

    private var searchView: SearchView? = null

    companion object {
        fun newInstance(type: Int): AbsentFragment {
            val args = Bundle()
            args.putInt(Constants.ABSENT_TYPE, type)
            val fragment = AbsentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(Constants.ABSENT_TYPE)

        presenter = AbsentFragmentPre(this)

        list = ArrayList()
        adapter = AdapterAbsentFragment(list, context, object : ItemClickListenerGeneric<AbsentEntity> {
            override fun ItemClick(position: Int, data: AbsentEntity?) {
                if (data!!.received == 0)
                    showDialogs(data, position, true)
                else
                    showDialogs(data, position, false)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_absent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView()
    }

    private fun initView() {
        initDialog()
        rcl_absent.layoutManager = LinearLayoutManager(context)
        rcl_absent.adapter = adapter
        presenter.getAbsentByClass(page, type)
    }

    private lateinit var dialog: Dialog
    lateinit var tv_title: TextView
    lateinit var tv_name: TextView
    lateinit var tv_date_start: TextView
    lateinit var tv_date_end: TextView
    lateinit var tv_content: TextView
    lateinit var tv_state: TextView

    private lateinit var btnValid: Button
    private lateinit var btnInValid: Button

    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(context, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_valid_absent)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_title = dialog.findViewById(R.id.tv_title)
        tv_name = dialog.findViewById(R.id.tv_name)
        tv_content = dialog.findViewById(R.id.tv_content)
        tv_date_start = dialog.findViewById(R.id.tv_date_start)
        tv_date_end = dialog.findViewById(R.id.tv_date_end)
        tv_state = dialog.findViewById(R.id.tv_state)

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnInValid = dialog.findViewById<Button>(R.id.btnInValid)
        btnValid = dialog.findViewById<Button>(R.id.btnValid)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        tv_title.text = "Chi tiết xin học thêm giờ"
    }

    fun showDialogs(data: AbsentEntity, position: Int, notValid: Boolean) {
        tv_title.text = "Chi tiết đơn xin nghỉ"

        var name = "<b>Học sinh: </b>" + data.fullNameStudent
        var start = "<b>Ngày bắt đầu: </b>" + data.absentStart
        var end = "<b>Ngày Kết thúc: </b>" + data.absentEnd
        var content = "<b>Nội dung: </b>" + data.content

        var state = ""
        when (data.received) {
            0 -> {
                state = "<b>Trạng thái: </b> Chưa duyêt"
            }
            1 -> {
                state = "<b>Trạng thái: </b> <font color=\'#3fbd2e\'>Đã xác nhận</font>"
            }
            2 -> {
                state = "<b>Trạng thái: </b> <font color=\'#ff0000\'>Đã từ chối</font>"
            }
        }

        tv_name.text = Html.fromHtml(name)
        tv_date_start.text = Html.fromHtml(start)
        tv_date_end.text = Html.fromHtml(end)
        tv_content.text = Html.fromHtml(content)
        tv_state.text = Html.fromHtml(state)

        if (notValid) {
            btnValid.visibility = View.VISIBLE
            btnValid.setOnClickListener {
                presenter.validAbsent(data, position, 1)
            }
            btnInValid.visibility = View.VISIBLE
            btnInValid.setOnClickListener {
                presenter.validAbsent(data, position, 2)
            }
        } else {
            btnValid.visibility = View.GONE
            btnInValid.visibility = View.GONE
        }

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
