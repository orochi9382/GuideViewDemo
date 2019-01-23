package com.lala.guidview.guid.model

import android.graphics.RectF
import android.view.View
import com.lala.guidview.guid.util.VIewUtils

class HighLightRectFView :HighLight{



    private var guidShape: HighLight.GuidShape

    private var round: Float

    private var padding: Int

    private var highLightOptions: HighLightOptions? = null

    private var rectF:RectF? = null

    constructor(rectF:RectF, guidShape: HighLight.GuidShape, round:Float, padding:Int){
        this.rectF = rectF
        this.guidShape = guidShape
        this.round = round
        this.padding = padding
    }

    fun setOptions(options: HighLightOptions) {
        this.highLightOptions = options
    }

    override fun getShape(): HighLight.GuidShape {
        return guidShape
    }

    override fun getRectF(view: View): RectF {

        return rectF as RectF
    }

    override fun getRadius(): Float {
        return Math.max(rectF!!.width()/2 , rectF!!.height()/2)
    }

    override fun getRound(): Float {
        return round
    }


    override fun getOptions(): HighLightOptions? {
        return this.highLightOptions
    }
}