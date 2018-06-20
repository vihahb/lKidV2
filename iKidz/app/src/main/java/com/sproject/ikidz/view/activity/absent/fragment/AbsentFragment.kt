package com.sproject.ikidz.view.activity.absent.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.AbsentEntity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.TextUtils
import com.sproject.ikidz.sdk.callback.ItemClickListener
import com.sproject.ikidz.view.activity.absent.presenter.AbsentFragmentPre
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_absent.*
import java.util.*

class AbsentFragment : BaseFragment(), IAbsentFragment, SearchView.OnQueryTextListener {
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
        adapter = AdapterAbsentFragment(list, context, object : ItemClickListener {
            override fun ItemClick(id: Int) {

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
        rcl_absent.layoutManager = LinearLayoutManager(context)
        rcl_absent.adapter = adapter
        presenter.getAbsentByClass(page, type)
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
