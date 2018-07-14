package com.snq.teacher.view.activity.drug.teacher.fragment

import com.snq.teacher.model.entity.DrugEntity
import com.snq.teacher.view.base.IBasicActivity

interface IDrugFragment : IBasicActivity {
    fun getDrugSuccess(data: List<DrugEntity>)
    fun getDrugError(errorMessage: String)
    fun validDrugSuccess(position: Int)
    fun validDrugError()
}