package com.sproject.ikidz.view.fragment.contacts

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.view.activity.learnActivity.LearnActivity
import com.sproject.ikidz.view.activity.schoolProfile.SchoolProfileActivity
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment() {

    companion object {

        fun newInstance(): ContactsFragment {
            val args = Bundle()
            val fragment = ContactsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        ln_learn_activitys.setOnClickListener {
            startActivity(Intent(context, LearnActivity::class.java))
        }
        ln_shcool_profile.setOnClickListener {
            startActivity(Intent(context, SchoolProfileActivity::class.java))
        }
    }

}