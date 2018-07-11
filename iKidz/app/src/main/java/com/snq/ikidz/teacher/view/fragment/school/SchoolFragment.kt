package com.snq.ikidz.teacher.view.fragment.school

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snq.ikidz.teacher.R
import com.snq.ikidz.teacher.model.entity.CountNotify
import com.snq.ikidz.teacher.presenter.SchoolPresenter
import com.snq.ikidz.teacher.sdk.Commons.Constants
import com.snq.ikidz.teacher.sdk.Utils.SharedUtils
import com.snq.ikidz.teacher.sdk.Utils.TextUtils
import com.snq.ikidz.teacher.view.activity.campaign.CampaignActivity
import com.snq.ikidz.teacher.view.activity.care.CareActivity
import com.snq.ikidz.teacher.view.activity.curentClass.CurentClassActivity
import com.snq.ikidz.teacher.view.activity.foreignActivity.ForeignActivity
import com.snq.ikidz.teacher.view.activity.learnProgram.LearnProgramActivity
import com.snq.ikidz.teacher.view.activity.school.SchoolActivity
import com.snq.ikidz.teacher.view.activity.shuttleBus.ShuttlesBusActivity
import com.snq.ikidz.teacher.view.activity.subscribeToEat.SubscribeToEatActivity
import com.snq.ikidz.teacher.view.base.BaseFragment
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
    var className: String? = null

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
        ln_lean_activity.setOnClickListener {
            startActivity(Intent(context, LearnProgramActivity::class.java))
        }

        ln_class.setOnClickListener {
            val intent = Intent(context, CurentClassActivity::class.java)
            intent.putExtra(Constants.TITLE, className)
            startActivity(intent)
        }

        ln_transfer.setOnClickListener {
            startActivity(Intent(context, ShuttlesBusActivity::class.java))
        }

        ln_other_activity.setOnClickListener {
            startActivity(Intent(context, ForeignActivity::class.java))
        }

        ln_school.setOnClickListener {
            startActivity(Intent(context, SchoolActivity::class.java))
        }

        ln_eat.setOnClickListener {
            startActivity(Intent(context, SubscribeToEatActivity::class.java))
        }
        ln_vote.setOnClickListener {
            startActivity(Intent(context, CampaignActivity::class.java))
        }
        ln_care.setOnClickListener {
            startActivity(Intent(context, CareActivity::class.java))
        }
    }

    override fun onResume() {
        className = SharedUtils.getInstance().getStringValue(Constants.CURRENT_CLASS_TEACHER_NAME)
        if (!TextUtils.isEmpty(className))
            tv_class_name.text = className
        super.onResume()
    }
}