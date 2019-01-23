package com.lala.guidview.guid.model

import android.graphics.Rect
import android.graphics.RectF
import android.support.v7.widget.ViewUtils
import android.view.View
import com.lala.guidview.guid.util.VIewUtils

class HightLightView:HighLight{

    private var lightView: View

    private var guidShape: HighLight.GuidShape

    private var round: Float

    private var padding: Int

    private var highLightOptions: HighLightOptions? = null

    private var rectF:RectF? = null

    constructor(view:View, guidShape: HighLight.GuidShape, round:Float, padding:Int,options: HighLightOptions?){
        this.lightView = view
        this.guidShape = guidShape
        this.round = round
        this.padding = padding
        this.highLightOptions = options
    }

    fun setOptions(options: HighLightOptions) {
        this.highLightOptions = options
    }

    override fun getShape(): HighLight.GuidShape {
        return guidShape
    }

    override fun getRectF(view: View): RectF? {
        rectF = RectF()
        val viewRect = VIewUtils.getViewRect(view,lightView)
        rectF!!.left = (viewRect.left - padding).toFloat()
        rectF!!.top = (viewRect.top - padding).toFloat()
        rectF!!.right = (viewRect.right + padding).toFloat()
        rectF!!.bottom = (viewRect.bottom + padding).toFloat()
        return rectF
    }

    override fun getRadius(): Float {
        return (Math.max(lightView.width /2 ,lightView.height/2)+padding).toFloat()
    }

    override fun getRound(): Float {
        return round
    }


    override fun getOptions(): HighLightOptions? {
        return this.highLightOptions
    }
}