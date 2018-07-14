package com.snq.teacher.view.activity.mbr.info

import com.snq.teacher.model.entity.MbrInfoEntity
import com.snq.teacher.view.base.IBasicActivity

interface IMbrInfoActivity : IBasicActivity {
    fun getMbrError(errorMessage: String)
    fun getMbrInfoSuccess(data: MbrInfoEntity)
}