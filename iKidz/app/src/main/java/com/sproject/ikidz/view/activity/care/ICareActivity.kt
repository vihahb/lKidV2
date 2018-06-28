package com.sproject.ikidz.view.activity.care

import com.sproject.ikidz.model.RESP.RESP_DataCareNews
import com.sproject.ikidz.view.base.IBasicActivity

interface ICareActivity : IBasicActivity {
    fun getCareNewsSuccess(respond: RESP_DataCareNews)
    fun getCareNewsError(errorMessage: String)
}