package com.snq.teacher.view.fragment.school

import com.snq.teacher.model.entity.CountNotify
import com.snq.teacher.view.base.IBasicActivity

interface ISchoolView : IBasicActivity {
    fun getSetNotify(data: CountNotify)
}