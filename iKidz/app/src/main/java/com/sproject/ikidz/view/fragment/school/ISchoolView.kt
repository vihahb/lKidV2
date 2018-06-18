package com.sproject.ikidz.view.fragment.school

import com.sproject.ikidz.model.entity.CountNotify
import com.sproject.ikidz.view.base.IBasicActivity

interface ISchoolView: IBasicActivity {
    fun getSetNotify(data: CountNotify)
}