package com.lala.guidview.guid.model

import android.graphics.RectF
import android.view.View

class HighlightRectF :HighLight{
    private var rectF:RectF
    private var guidShape:HighLight.GuidShape
    private var round:Float
    private var highLightOptions: HighLightOptions? = null

    constructor(rectF: RectF, guidShape: HighLight.GuidShape, round: Float,highLightOptions: HighLightOptions?) {
        this.rectF = rectF
        this.guidShape = guidShape
        this.round = round
        this.highLightOptions = highLightOptions
    }

    fun setOptions(options: HighLightOptions) {
        this.highLightOptions = options
    }

    override fun getShape(): HighLight.GuidShape {
        return guidShape
    }

    override fun getRectF(view: View): RectF? {
        return rectF
    }

    override fun getRadius(): Float {
       return return Math.max(rectF.width()/2,rectF.height()/2)
    }

    override fun getRound(): Float {
        return round
    }

    override fun getOptions(): HighLightOptions? {
      return highLightOptions
    }
}