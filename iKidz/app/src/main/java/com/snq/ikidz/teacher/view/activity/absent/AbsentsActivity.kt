package com.snq.ikidz.teacher.view.activity.absent

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_absents.*

class AbsentsActivity : BaseActivity() {

    lateinit var vpAdapter: ViewPagerAbsent

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}
