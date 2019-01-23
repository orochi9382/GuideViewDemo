package com.lala.guidview.guid.core

import android.app.Activity
import android.support.v4.app.Fragment
import com.lala.guidview.guid.model.GuidePage

class Builder {
    var activity: Activity? = null
    var fragment: Fragment? = null
    var label:String? = null
    var alwaysShow :Boolean = false
    var showCount :Int = 1
    var guidePages : MutableList<GuidePage>  = mutableListOf()
    var guidPage:GuidePage? = null


    constructor(activity: Activity){
        this.activity = activity

    }

    constructor(fragment: Fragment){
        this.fragment = fragment
        this.activity = fragment.activity
    }

    public fun alwaysShow(show:Boolean):Builder{
        this.alwaysShow = show
        return this
    }

    fun addGuidePage(guidPage: GuidePage):Builder{
        this.guidePages.add(guidPage)
        return this
    }

    fun setShowCount(count:Int):Builder{
        this.showCount = count
        return this
    }

    fun setLabel(label:String):Builder{
        this.label = label
        return this
    }

    fun build():GuideContrller{
        check()
        return GuideContrller(this)

    }

    fun show():GuideContrller{
        check()
        val controller = GuideContrller(this)
        controller.show()
        return controller
    }

    fun check(){
        if (activity == null && fragment == null){
            throw IllegalStateException("")
        }
    }



}