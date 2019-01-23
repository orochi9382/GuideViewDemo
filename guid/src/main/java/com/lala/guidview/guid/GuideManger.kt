package com.lala.guidview.guid

import android.app.Activity
import android.support.v4.app.Fragment
import com.lala.guidview.guid.core.Builder

class GuideManger {
    companion object {
        val TAG:String = "GuideManger"
        val SUCCESS:Int = 1
        val FAILED:Int = -1

        public fun with(activity:Activity):Builder{
            return Builder(activity)
        }

        public fun with(fragment: Fragment):Builder{
            return Builder(fragment)
        }
    }
}