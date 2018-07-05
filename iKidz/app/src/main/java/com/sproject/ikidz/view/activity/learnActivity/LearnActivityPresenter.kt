package com.sproject.ikidz.view.activity.learnActivity

import com.sproject.ikidz.R
import com.sproject.ikidz.model.RESP.RESP_Basic
import com.sproject.ikidz.model.RESP.RESP_DataLearnActivity
import com.sproject.ikidz.model.entity.DataParamsAddLearnEntity
import com.sproject.ikidz.model.entity.ErrorEntity
import com.sproject.ikidz.model.entity.ParamsAddLearnEntity
import com.sproject.ikidz.model.server.CreateOrUpdateLearnActivity
import com.sproject.ikidz.model.server.GetLearnActivity
import com.sproject.ikidz.sdk.Commons.Constants
import com.sproject.ikidz.sdk.Utils.NetworkUtils
import com.sproject.ikidz.sdk.Utils.SharedUtils
import java.util.*

class LearnActivityPresenter(private var view: ILearnActivityView) {
    fun getLearnActivity(page: Int) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)
        view.showProgressBar(true, true, "Loading...")
        object : GetLearnActivity(page, classId, token, linkApi) {
            override fun onSucces(result: RESP_DataLearnActivity) {
                view.closeProgressBar()
                view.getLearnActivitySuccess(result.data.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getLearnActivityError(s.errorMessage)
            }

        }
    }

    fun createOrUpdateLearn(pos: Int, type: Int, id: Int?, learningMorning: String,
                            learningAfternoon: String, date: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }
        var classId = SharedUtils.getInstance().getIntValue(Constants.CURRENT_CLASS_TEACHER_ID)
        val token = SharedUtils.getInstance().getStringValue(Constants.CURRENT_TOKEN)
        val linkApi = SharedUtils.getInstance().getStringValue(Constants.LINK_API)

        var mesage = ""
        when (type) {
            Constants.TYPE_ADD -> {
                mesage = "Đang tạo hoạt động học..."
            }
            Constants.TYPE_EDIT -> {
                mesage = "Đang cập nhật hoạt động học..."
            }
        }
        view.showProgressBar(true, true, mesage)

        val activityes = ArrayList<ParamsAddLearnEntity>()
        if (id != null)
            activityes.add(ParamsAddLearnEntity(learningMorning, learningAfternoon, id))
        else
            activityes.add(ParamsAddLearnEntity(learningMorning, learningAfternoon, null))
        val dataparm = DataParamsAddLearnEntity(activityes, classId, date)

        object : CreateOrUpdateLearnActivity(dataparm, token, linkApi) {
            override fun onSucces(news: RESP_Basic?) {
                view.closeProgressBar()
                view.createOrUpdateSuccess(learningMorning, learningAfternoon, date, pos)
            }

            override fun onError(s: ErrorEntity?) {
                view.closeProgressBar()
                view.createOrUpdateError()
            }

        }
    }
}