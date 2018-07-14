package com.snq.teacher.view.activity.care

import com.snq.teacher.model.RESP.RESP_DataCareNews
import com.snq.teacher.view.base.IBasicActivity

interface ICareActivity : IBasicActivity {
    fun getCareNewsSuccess(respond: RESP_DataCareNews)
    fun getCareNewsError(errorMessage: String)
}