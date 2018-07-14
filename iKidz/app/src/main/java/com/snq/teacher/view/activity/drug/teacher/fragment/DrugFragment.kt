package com.snq.teacher.view.activity.drug.teacher.fragment

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
import com.snq.teacher.R
import com.snq.teacher.model.entity.DrugEntity
import com.snq.teacher.sdk.Commons.Constants
import com.snq.teacher.sdk.Utils.TextUtils
import com.snq.teacher.sdk.callback.ItemClickListenerGeneric
import com.snq.teacher.view.activity.drug.teacher.fragment.presenter.DrugFragmentPre
import com.snq.teacher.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_drug.*

class DrugFragment : BaseFragment(), IDrugFragment, SearchView.OnQueryTextListener {
    override fun validDrugSuccess(position: Int) {
        showLongToast("Xác nhận đơn dặn thuốc thành công!")
        list[position].received = 1
        adapter.notifyItemChanged(position)
        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun validDrugError() {
        if (dialog.isShowing)
            dialog.dismiss()
        showLongToast("Xác nhận đơn dặn thuốc không thành công!")
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

    override fun getDrugError(errorMessage: String) {
        tv_message.visibility = View.VISIBLE
        tv_message.text = errorMessage
    }

    override fun getDrugSuccess(data: List<DrugEntity>) {
        if (data.isEmpty()) {
            tv_message.visibility = View.VISIBLE
            tv_message.text = activity!!.resources.getString(R.string.mesage_no_data)
        }

        list.addAll(data)
        adapter.notifyDataSetChanged()


    }

    var type = -1
    var page = 1

    lateinit var presenter: DrugFragmentPre
    lateinit var adapter: AdapterDrugFragment
    lateinit var list: ArrayList<DrugEntity>
    private var searchView: SearchView? = null

    companion object {
        fun newInstance(type: Int): DrugFragment {
            val args = Bundle()
            args.putInt(Constants.DRUG_TYPE, type)
            val fragment = DrugFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getInt(Constants.DRUG_TYPE)

        presenter = DrugFragmentPre(this)

        list = ArrayList()
        adapter = AdapterDrugFragment(list, context, object : ItemClickListenerGeneric<DrugEntity> {
            override fun ItemClick(pos: Int, data: DrugEntity) {
                if (data.received == 0)
                    showDialogs(data, pos, true)
                else
                    showDialogs(data, pos, false)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drug, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView()
    }

    private fun initView() {
        initDialog()
        rcl_drug.layoutManager = LinearLayoutManager(context)
        rcl_drug.adapter = adapter
        presenter.getDrugByClass(page, type)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<DrugEntity>()

        for (s in list) {
            if (TextUtils.unicodeToKoDauLowerCase(s.fullNameStudent.toLowerCase()).contains(TextUtils.unicodeToKoDauLowerCase(name.toLowerCase()))) {
                filterdNames.add(s)
            }
        }
        adapter.filterList(filterdNames)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_attendance_out, menu)
        val itemSearch = menu!!.findItem(R.id.search_view_attendance_out)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private lateinit var dialog: Dialog
    lateinit var tv_title: TextView
    lateinit var tv_name: TextView
    lateinit var tv_date_start: TextView
    lateinit var tv_date_end: TextView
    lateinit var tv_content: TextView

    private lateinit var btnUpdate: Button

    @SuppressLint("NewApi")
    private fun initDialog() {
        dialog = Dialog(context, R.style.Theme_Transparent)
        dialog.setContentView(R.layout.dialog_dan_thuoc)
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        tv_title = dialog.findViewById(R.id.tv_title)
        tv_name = dialog.findViewById(R.id.tv_name)
        tv_content = dialog.findViewById(R.id.tv_content)
        tv_date_start = dialog.findViewById(R.id.tv_date_start)
        tv_date_end = dialog.findViewById(R.id.tv_date_end)

        val btnClose = dialog.findViewById<Button>(R.id.btnClose)
        btnUpdate = dialog.findViewById<Button>(R.id.btnUpdate)

        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnUpdate.text = "Xác nhận"

        tv_title.text = "Chi tiết dặn thuốc"
    }

    fun showDialogs(data: DrugEntity, position: Int, notValid: Boolean) {
        var name = "<b>Học sinh: </b>" + data.fullNameStudent
        var start = "<b>Ngày bắt đầu: </b>" + data.startDate
        var end = "<b>Ngày Kết thúc: </b>" + data.endDate
        var content = "<b>Nội dung: </b>" + data.notes

        tv_name.text = Html.fromHtml(name)
        tv_date_start.text = Html.fromHtml(start)
        tv_date_end.text = Html.fromHtml(end)
        tv_content.text = Html.fromHtml(content)

        if (notValid) {
            btnUpdate.visibility = View.VISIBLE
            btnUpdate.setOnClickListener {
                presenter.validDrug(data, position)
            }
        } else
            btnUpdate.visibility = View.GONE


        dialog.show()
    }
}