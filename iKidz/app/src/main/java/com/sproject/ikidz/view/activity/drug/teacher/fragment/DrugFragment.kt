package com.sproject.ikidz.view.activity.drug.teacher.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.DrugEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.callback.ItemClickListener
import com.sproject.ikidz.view.activity.drug.teacher.fragment.presenter.DrugFragmentPre
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_drug.*

class DrugFragment : BaseFragment(), IDrugFragment, SearchView.OnQueryTextListener {
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
        adapter = AdapterDrugFragment(list, context, object : ItemClickListener {
            override fun ItemClick(id: Int) {

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
        rcl_drug.layoutManager = LinearLayoutManager(context)
        rcl_drug.adapter = adapter
        presenter.getDrugByClass(page, type)
    }

    private fun filterItem(name: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<DrugEntity>()

        for (s in list!!) {
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
}