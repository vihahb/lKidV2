package com.sproject.ikidz.view.activity.mbr.info

import com.sproject.ikidz.model.entity.MbrInfoEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IMbrInfoActivity :IBasicActivity {
    fun getMbrError(errorMessage: String)
    fun getMbrInfoSuccess(data: MbrInfoEntity)
}