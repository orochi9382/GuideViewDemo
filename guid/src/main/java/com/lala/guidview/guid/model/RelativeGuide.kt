package com.lala.guidview.guid.model

import android.support.annotation.IntDef
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.lala.guidview.guid.core.GuideContrller
import com.lala.guidview.guid.listener.OnLayoutInflatedListener

class RelativeGuide {
    var highlight:HighLight? = null
    var layout:Int = 0
    var padding:Int = 0
    var gravity:Int = 0
    var onLayoutInflatedListener :OnLayoutInflatedListener? = null

    constructor(layout: Int =0, gravity: Int = 0, padding: Int = 0,onLayoutInflatedListener: OnLayoutInflatedListener) {
        this.layout = layout
        this.padding = padding
        this.gravity = gravity
        this.onLayoutInflatedListener = onLayoutInflatedListener
    }

    constructor(layout: Int =0, gravity: Int = 0, padding: Int = 0) {
        this.layout = layout
        this.padding = padding
        this.gravity = gravity
    }


   class MarginInfo{
       var leftMargin:Int = 0
       var rightMargin:Int = 0
       var topMargin:Int = 0
       var bottomMargin:Int = 0
       var gravity:Int = 0
   }

    fun getGuidLayout(viewGroup: ViewGroup,guideContrller: GuideContrller):View{
        var view = LayoutInflater.from(viewGroup.context).inflate(layout,viewGroup,false)
        onLayoutInflatedListener!!.onLayoutInflated(view, guideContrller)
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        var marginInfo = getMarginInfo(gravity,viewGroup,view)
        layoutParams.gravity = marginInfo.gravity
        layoutParams.leftMargin = marginInfo.leftMargin
        layoutParams.rightMargin  = marginInfo.rightMargin
        layoutParams.bottomMargin = marginInfo.bottomMargin
        layoutParams.topMargin = marginInfo.topMargin
        view.layoutParams = layoutParams
        return view

    }

    fun getMarginInfo(gravity: Int,viewGroup: ViewGroup,view: View):MarginInfo{
        var marginInfo = MarginInfo()
        val rectF = highlight!!.getRectF(viewGroup)

        when(gravity){
            Gravity.LEFT->{
                marginInfo.gravity = Gravity.RIGHT
                marginInfo.rightMargin = (viewGroup.width-rectF!!.left+padding).toInt()
                marginInfo.topMargin = rectF.top.toInt()
            }

            Gravity.TOP->{
                marginInfo.gravity = Gravity.BOTTOM
                marginInfo.bottomMargin = (viewGroup.height-rectF!!.top+padding).toInt()
                marginInfo.leftMargin = rectF.left.toInt()
            }

            Gravity.RIGHT->{
                marginInfo.leftMargin = (rectF!!.right +padding).toInt()
                marginInfo.topMargin = rectF.top.toInt()
            }

            Gravity.BOTTOM->{
                marginInfo.topMargin = (rectF!!.bottom +padding).toInt()
                marginInfo.leftMargin = rectF.left.toInt()
            }
        }
        return marginInfo
    }





}