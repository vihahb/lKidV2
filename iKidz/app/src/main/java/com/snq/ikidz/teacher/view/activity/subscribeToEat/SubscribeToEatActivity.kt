package com.snq.ikidz.teacher.view.activity.subscribeToEat

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.view.activity.subscribeToEat.fragment.SubscribeToEatFragment
import com.snq.ikidz.teacher.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_subscribe_to_eat.*

class SubscribeToEatActivity : BaseActivity() {

    lateinit var vpAdapter: ViewPageEat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe_to_eat)
        initToolbar("THÔNG TIN ĐĂNG KÝ ĂN", true)
        initView()
    }

    private fun initView() {
        vpAdapter = ViewPageEat(supportFragmentManager, this)
        vpEat.adapter = vpAdapter
        tablayoutEat.setupWithViewPager(vpEat)
        vpEat.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
            override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }


    class ViewPageEat(fm: FragmentManager?, private val context: Context) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    SubscribeToEatFragment.newInstance(1)
                }
                1 -> {
                    SubscribeToEatFragment.newInstance(2)
                }
                else -> {
                    SubscribeToEatFragment.newInstance(1)
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> {
                    context.resources.getString(R.string.title_subscribe_eat)
                }
                1 -> {
                    context.resources.getString(R.string.title_log_eat)
                }
                else -> {
                    context.resources.getString(R.string.title_subscribe_eat)
                }
            }
        }

    }
}


