package com.snq.teacher.view.activity.learnOverTime

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.snq.teacher.R
import com.snq.teacher.view.activity.learnOverTime.fragment.LearnOverTimeFragment

class ViewPageLearnMoreTime(fm: FragmentManager?, private val context: Context) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LearnOverTimeFragment.newInstance(1)
            }
            1 -> {
                LearnOverTimeFragment.newInstance(2)
            }
            2 -> {
                LearnOverTimeFragment.newInstance(0)
            }
            else -> {
                LearnOverTimeFragment.newInstance(1)
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