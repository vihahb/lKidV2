package com.snq.ikidz.teacher.view.activity.care

import com.snq.ikidz.teacher.model.RESP.RESP_DataCareNews
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ICareActivity : IBasicActivity {
    fun getCareNewsSuccess(respond: RESP_DataCareNews)
    fun getCareNewsError(errorMessage: String)
}