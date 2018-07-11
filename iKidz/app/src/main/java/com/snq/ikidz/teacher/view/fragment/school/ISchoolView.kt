package com.snq.ikidz.teacher.view.fragment.school

import com.snq.ikidz.teacher.model.entity.CountNotify
import com.snq.ikidz.teacher.view.base.IBasicActivity

interface ISchoolView : IBasicActivity {
    fun getSetNotify(data: CountNotify)
}