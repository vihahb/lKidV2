package com.sproject.ikidz.view.activity.absent

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.sproject.ikidz.R
import com.sproject.ikidz.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_absents.*
import kotlinx.android.synthetic.main.activity_home.*

class AbsentsActivity : BaseActivity(), SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    lateinit var vpAdapter: ViewPagerAbsent

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absents)
        initToolbar("XIN NGHỈ", true)
        initView()
    }

    private var titles = ""

    private fun initView() {
        vpAdapter = ViewPagerAbsent(supportFragmentManager, this)
        vpAbsent.adapter = vpAdapter
        tablayoutAbsent.setupWithViewPager(vpAbsent)
        vpAbsent.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
            override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                titles = vpAdapter.getPageTitle(position).toString()
                setTitleToolbar(titles)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_absent, menu!!)

        val itemSearch = menu.findItem(R.id.search_view_absent)
        searchView = itemSearch.actionView as SearchView
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView!!.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
