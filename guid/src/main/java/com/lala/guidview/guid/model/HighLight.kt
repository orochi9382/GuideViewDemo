package com.lala.guidview.guid.model

import android.graphics.RectF
import android.view.View

interface HighLight {

    fun getShape(): GuidShape

    fun getRectF(view: View): RectF?

    fun getRadius():Float

    fun getRound():Float

    fun getOptions():HighLightOptions?

     enum class GuidShape{
        CIRCLE,RECTANGLE,OVAL,ROUND_RECTANGLE
    }
}