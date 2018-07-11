package com.snq.teacher.view.activity.learnProgram

import com.snq.teacher.R
import com.snq.teacher.model.RESP.RESP_DataMonth
import com.snq.teacher.model.entity.ErrorEntity
import com.snq.teacher.model.server.GetLearnProgram
import com.snq.teacher.sdk.Utils.NetworkUtils

class LearnProgramPre(private var view: ILearnProgram) {

    fun getLEarnProgram(date_start: String, date_end: String) {
        if (!NetworkUtils.isConnected(view.activity)) {
            view.showLongToast(view.activity.resources.getString(R.string.error_network))
            return
        }

        view.showProgressBar(true, true, "Loading...")
        object : GetLearnProgram(date_start, date_end) {
            override fun onSucces(res: RESP_DataMonth?) {
                view.closeProgressBar()
                view.getLearnProgramSuccess(res!!.data)
            }

            override fun onError(s: ErrorEntity) {
                view.closeProgressBar()
                view.getLearnProgramerror(s.errorMessage)
            }

        }

    }

}