package com.snq.teacher.view.activity.drug.teacher

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.snq.teacher.R
import com.snq.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_drug.*

class DrugActivity : BaseActivity() {
    private var titles = ""
    private lateinit var vpAdapter: ViewPageDrug

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug)
        initToolbar("DẶN THUỐC", true)
        initView()
    }

    private fun initView() {
        vpAdapter = ViewPageDrug(supportFragmentManager, this)
        vpDrug.adapter = vpAdapter
        tablayoutDrug.setupWithViewPager(vpDrug)
        vpDrug.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
