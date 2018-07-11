package com.snq.ikidz.teacher.view.activity.absent

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.view.activity.absent.fragment.AbsentFragment

class ViewPagerAbsent(fm: FragmentManager?, private val context: Context) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AbsentFragment.newInstance(1)
            }
            1 -> {
                AbsentFragment.newInstance(2)
            }
            2 -> {
                AbsentFragment.newInstance(0)
            }
            else -> {
                AbsentFragment.newInstance(1)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.resources.getString(R.string.title_today)
            }
            1 -> {
                context.resources.getString(R.string.title_future)
            }
            2 -> {
                context.resources.getString(R.string.title_all)
            }
            else -> {
                context.resources.getString(R.string.title_today)
            }
        }
    }

}
