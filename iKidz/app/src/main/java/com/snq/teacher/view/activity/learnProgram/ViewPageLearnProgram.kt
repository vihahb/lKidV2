package com.snq.teacher.view.activity.learnProgram

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.snq.teacher.R
import com.snq.teacher.model.entity.DataWeek
import com.snq.teacher.view.activity.learnProgram.viewpager.LearnProgramFragment

class ViewPageLearnProgram(fm: FragmentManager?, private val context: Context, private var data: List<DataWeek>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return LearnProgramFragment.newInstance(data[position])
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.resources.getString(R.string.title_week_1)
            }
            1 -> {
                context.resources.getString(R.string.title_week_2)
            }
            2 -> {
                context.resources.getString(R.string.title_week_3)
            }
            3 -> {
                context.resources.getString(R.string.title_week_4)
            }
            else -> {
                context.resources.getString(R.string.title_week_1)
            }
        }
    }

}