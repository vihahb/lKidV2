package com.sproject.ikidz.view.activity.learnOverTime.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.LearnOverTimeEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.callback.ItemClickListener
import com.sproject.ikidz.view.activity.learnOverTime.fragment.presenter.LearnOverTimeFragmentPre
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ot.*

class LearnOverTimeFragment : BaseFragment(), ILearnOverTimeFragment, SearchView.OnQueryTextListener {
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
        adapter = AdapterLearnOTFragment(list, context, object : ItemClickListener {
            override fun ItemClick(id: Int) {

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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity!!.menuInflater.inflate(R.menu.menu_attendance_out, menu)
        val itemSearch = menu!!.findItem(R.id.search_view_attendance_out)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }
}