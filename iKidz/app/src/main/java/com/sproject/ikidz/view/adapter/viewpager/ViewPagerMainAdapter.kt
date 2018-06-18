package com.sproject.ikidz.view.adapter.viewpager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sproject.ikidz.R
import com.sproject.ikidz.view.fragment.*
import com.sproject.ikidz.view.fragment.news.NewsfeedFragment
import com.sproject.ikidz.view.fragment.school.SchoolFragment

class ViewPagerMainAdapter(fm: FragmentManager?, private val context: Context) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return NewsfeedFragment.newInstance()
            }
            1 -> {
                return SchoolFragment.newInstance()
            }
            2 -> {
                return ContactsFragment.newInstance()
            }
            3 -> {
                return PhoneBooksFragment.newInstance()
            }
            4 -> {
                return MessageFragment.newInstance()
            }
            else -> {
                return NewsfeedFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return context.resources.getString(R.string.title_newsfeed)
            }
            1 -> {
                return context.resources.getString(R.string.title_school)
            }
            2 -> {
                return context.resources.getString(R.string.title_contacts)
            }
            3 -> {
                return context.resources.getString(R.string.title_phonebooks)
            }
            4 -> {
                return context.resources.getString(R.string.title_message)
            }
            else -> {
                return context.resources.getString(R.string.title_newsfeed)
            }
        }
    }
}