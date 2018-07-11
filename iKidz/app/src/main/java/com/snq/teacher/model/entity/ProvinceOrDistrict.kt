package com.snq.teacher.model.entity

import com.google.gson.annotations.Expose

/**
 * Created by User: Vi-PC
 * Author: Vũ Hà Vi
 * Date: 6/6/2018
 * Time: 11:50 PM
 * Email: vihahb@gmail.com
 */
class ProvinceOrDistrict {
    @Expose
    var id: Int = 0
    @Expose
    var title: String? = null

    constructor() {}

    constructor(id: Int, title: String) {
        this.id = id
        this.title = title
    }

    override fun toString(): String {
        return "ProvinceOrDistrict{" +
                "id=" + id +
                ", title='" + title + '\''.toString() +
                '}'.toString()
    }
}