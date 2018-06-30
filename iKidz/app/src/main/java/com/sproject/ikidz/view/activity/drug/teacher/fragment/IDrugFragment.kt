package com.sproject.ikidz.view.activity.drug.teacher.fragment

import com.sproject.ikidz.model.entity.DrugEntity
import com.sproject.ikidz.view.base.IBasicActivity

interface IDrugFragment : IBasicActivity {
    fun getDrugSuccess(data: List<DrugEntity>)
    fun getDrugError(errorMessage: String)
    fun validDrugSuccess(position: Int)
    fun validDrugError()
}