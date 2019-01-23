package com.lala.guidview.guid.core

import android.app.Activity
import android.content.SharedPreferences
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.lala.guidview.guid.GuideManger
import com.lala.guidview.guid.model.GuidePage
import java.lang.IllegalStateException

class GuideContrller {

    private var activity:Activity? = null
    private var fragment:Fragment? = null
    private var label:String? = null
    private var alwaysShow:Boolean = false
    private var guidPages: MutableList<GuidePage>? = null
    private var current:Int = 0
    private var mainLayout: FrameLayout? = null
    private var isShowing:Boolean = false
    private var indexOfChild = -1
    private var showCounts :Int = 1
    private var sp: SharedPreferences

    constructor(build:Builder){
        this.activity = build.activity
        this.fragment  = build.fragment
        this.label = build.label
        this.alwaysShow  = build.alwaysShow
        this.guidPages = build.guidePages
        this.showCounts = build.showCount
        val anchor = activity!!.window.decorView//activity!!.findViewById<View>(android.R.id.content)

        mainLayout = anchor as FrameLayout?
        sp = activity!!.getSharedPreferences(GuideManger.TAG, Activity.MODE_PRIVATE)


    }


    fun show(){
        val showed = sp.getInt(label, 0)
        if (!alwaysShow) {
            if (showed >= showCounts) {
                return
            }
        }
        if (isShowing)
            return
        isShowing = true

//        activity!!.runOnUiThread{
//            if (guidPages == null || guidPages!!.size == 0){
//                throw IllegalStateException("no guid pages")
//            }
//
//
//        }
        current = 0
        showGuidPage()
        sp.edit().putInt(label, showed + 1).apply()
    }

    private fun showGuidPage() {
        val guidpage = guidPages!![current]
        val guideLayout = GuideLayout(activity!!,guidpage,this)

        if(mainLayout != null){

            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            (mainLayout as FrameLayout).addView(guideLayout)
        }


        isShowing = true
    }



}
