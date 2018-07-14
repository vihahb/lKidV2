package com.snq.ikidz.teacher.view.activity.mbr.info

import com.snq.ikidz.teacher.model.entity.MbrInfoEntity
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface IMbrInfoActivity : IBasicActivity {
    fun getMbrError(errorMessage: String)
    fun getMbrInfoSuccess(data: MbrInfoEntity)
}