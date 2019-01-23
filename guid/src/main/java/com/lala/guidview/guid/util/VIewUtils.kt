package com.lala.guidview.guid.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewParent
import android.view.Window
import android.view.Window.ID_ANDROID_CONTENT



class VIewUtils {

    companion object {
         val FRAGMENT_CON = "NoSaveStateFrameLayout"
        fun getViewRect(parentView: View,childView:View ): Rect {
            var context :Context = childView.context
            var result = Rect()
            var tempRect = Rect()
            if (parentView == childView){
                childView.getHitRect(result)
               return result
            }

            var tmp = childView

            while (tmp != parentView ){
                tmp.getHitRect(tempRect)
                if (!tmp.javaClass.equals(FRAGMENT_CON) ){
                    result.left += tempRect.left
                    result.top += tempRect.top
                }
                tmp = tmp.parent as View
            }

            val activity =  childView.context as Activity
            val rectangle = Rect()
            val window = activity.getWindow()
            window.getDecorView().getWindowVisibleDisplayFrame(rectangle)
            val statusBarHeight = rectangle.top


//            result.top  = result.top + statusBarHeight
            result.right  = result.left +childView.measuredWidth
            result.bottom  = result.top +childView.measuredHeight

//            val position = IntArray(2)
//            childView.getLocationInWindow(position)
//            result.right = position[0]+childView.measuredWidth/2
//            result.bottom = position[1]+childView.measuredHeight/2
            return result
        }

    }
}