package com.lala.guidview.guid.model

import android.os.Build
import android.view.View
import com.lala.guidview.guid.listener.OnHighLightDrawedListener

class HighLightOptions {
     var onClickListener:View.OnClickListener? = null
     var onHighLightDrawedListener:OnHighLightDrawedListener? = null
    var relativeGuide:RelativeGuide? = null

    companion object class Build{
        private var options:HighLightOptions = HighLightOptions()


        fun setOnClickListener(onClickListener:View.OnClickListener?): Build {
            options.onClickListener = onClickListener
            return this@Build
        }

        fun setOnHighLightDrawedListener(onHighLightDrawedListener:OnHighLightDrawedListener?):Build{
            options.onHighLightDrawedListener = onHighLightDrawedListener
            return this@Build
        }

        fun setRelativeGuide(relativeGuide: RelativeGuide):Build{
            options.relativeGuide  = relativeGuide
            return this@Build
        }

        fun create():HighLightOptions{
            return options
        }
    }





}