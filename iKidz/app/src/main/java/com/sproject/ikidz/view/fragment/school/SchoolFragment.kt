package com.sproject.ikidz.view.fragment.school

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sproject.ikidz.R
import com.sproject.ikidz.model.entity.CountNotify
import com.sproject.ikidz.presenter.SchoolPresenter
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.SharedUtils
import com.sproject.ikidz.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : BaseFragment(), ISchoolView {
    override fun getSetNotify(data: CountNotify) {
        val class_name = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
        if (!class_name.isEmpty())
            tv_notify_class.text = class_name

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
    }
}