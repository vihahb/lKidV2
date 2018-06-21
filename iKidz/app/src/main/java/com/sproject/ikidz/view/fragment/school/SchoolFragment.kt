package com.sproject.ikidz.view.fragment.school

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.CountNotify
import com.sproject.ikidz.presenter.SchoolPresenter
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.activity.curentClass.CurentClassActivity
import com.sproject.ikidz.view.activity.foreignActivity.ForeignActivity
import com.sproject.ikidz.view.activity.school.SchoolActivity
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : BaseFragment(), ISchoolView {
    override fun getSetNotify(data: CountNotify) {
//        val className = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
//        if (!className.isEmpty())
//            tv_notify_class.text = className

        if (data.newsNew > 0) {
            tv_notify_School.visibility = View.VISIBLE
            tv_notify_School.text = "" + data.newsNew
        }

        if (data.registerEat > 0) {
            tv_notify_eat.visibility = View.VISIBLE
            tv_notify_eat.text = "" + data.registerEat
        }
        if (data.extracurricular > 0) {
            tv_notify_activity_other.visibility = View.VISIBLE
            tv_notify_activity_other.text = "" + data.extracurricular
        }
        if (data.studyProgram > 0) {
            tv_notify_learn.visibility = View.VISIBLE
            tv_notify_learn.text = "" + data.studyProgram
        }
        if (data.poll > 0) {
            tv_notify_vote.visibility = View.VISIBLE
            tv_notify_vote.text = "" + data.poll
        }
        if (data.takeCare > 0) {
            tv_notify_care.visibility = View.VISIBLE
            tv_notify_care.text = "" + data.takeCare
        }
    }

    companion object {

        fun newInstance(): SchoolFragment {
            val args = Bundle()
            val fragment = SchoolFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var presenter: SchoolPresenter
    var className = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SchoolPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_school, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        presenter.getCountNotify()
        initOnClick()
    }

    private fun initOnClick() {
        ln_class.setOnClickListener {
            val intent = Intent(context, CurentClassActivity::class.java)
            intent.putExtra(Constants.TITLE, className)
            startActivity(intent)
        }

        ln_other_activity.setOnClickListener {
            startActivity(Intent(context, ForeignActivity::class.java))

        }

        ln_school.setOnClickListener {
            startActivity(Intent(context, SchoolActivity::class.java))
        }
    }

    override fun onResume() {
//        className = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
//        if (!className.isEmpty())
//            tv_class_name.text = className
        super.onResume()
    }
}