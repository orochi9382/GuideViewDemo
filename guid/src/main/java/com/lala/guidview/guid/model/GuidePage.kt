package com.lala.guidview.guid.model

import android.graphics.RectF
import android.view.View
import com.lala.guidview.guid.listener.OnLayoutInflatedListener
import java.util.ArrayList

class GuidePage {
    private var hightLightPages:MutableList<HighLight>  = mutableListOf();

    private var everyWhereCanCancle:Boolean = true
    private var backgroundColor:Int = 0
    private var layoutId = 0
    private var clickToDismissId:Array<Int>? = null
    private var inflaterListener:OnLayoutInflatedListener? = null

    companion object {
        fun newInstance(): GuidePage {
            return GuidePage()
        }
    }

    fun addHighLight(view: View) :GuidePage{
        return addHighLight(view, HighLight.GuidShape.RECTANGLE, 0f, 0, null,null)
    }

    fun addHighLight(view:View ,shape: HighLight.GuidShape):GuidePage{
        return addHighLight(view, shape, 0f, 0, null,null)
    }

    fun addHighLight(view:View ,options: HighLightOptions):GuidePage{
        return addHighLight(view, HighLight.GuidShape.RECTANGLE, 0f, 0, null,options)
    }

    fun addHighLight(view:View ,shape: HighLight.GuidShape,padding: Int):GuidePage{
        return addHighLight(view, shape, 0f, padding, null,null)
    }



    fun addHighLight(view:View ,shape: HighLight.GuidShape,padding: Int,round: Float):GuidePage{
        return addHighLight(view, shape, round, padding, null,null)
    }

    fun addHighLight(view:View ,shape: HighLight.GuidShape,padding: Int,round: Float,options: HighLightOptions):GuidePage{
        return addHighLight(view, shape, round, padding, null,options)
    }


    /**
     * @param view UI view
     * @param guidShape highLight shape
     * @round corner radious
     * @param padding 邊距
     * @param relativeGuide 關聯highlight區
     * @param options highligth event
     */
    fun addHighLight(view: View,guidShape: HighLight.GuidShape,round:Float,padding :Int,relativeGuide:RelativeGuide?,options: HighLightOptions?):GuidePage{

            val highLightView = HightLightView(view,guidShape,round,padding,options)
            if (relativeGuide != null){
               relativeGuide.highlight = highLightView
                highLightView.setOptions(HighLightOptions.Build().setRelativeGuide(relativeGuide).create())
            }

        if (options != null){
            if(options.relativeGuide != null){
                options.relativeGuide!!.highlight = highLightView
            }
            highLightView.setOptions(options)
        }
            hightLightPages.add(highLightView)

        return this
    }


    fun addHighLight(rectF: RectF) :GuidePage{
        return addHighLight(rectF, HighLight.GuidShape.RECTANGLE, 0f, null, null)
    }

    fun addHighLight(rectF: RectF,guidShape: HighLight.GuidShape) :GuidePage{
        return addHighLight(rectF, guidShape, 0f, null,null)

        fun addHighLight(rectF:RectF ,shape: HighLight.GuidShape,options: HighLightOptions):GuidePage{
            return addHighLight(rectF, guidShape, 0f, null,options)
        }
    }

    fun addHighLight(rectF: RectF,guidShape: HighLight.GuidShape,round: Float) :GuidePage{
        return addHighLight(rectF, guidShape, round, null,null)
    }

    fun addHighLight(rectF: RectF,guidShape: HighLight.GuidShape,round: Float,options: HighLightOptions) :GuidePage{
        return addHighLight(rectF, guidShape, round, null,options)
    }



    /**
     * @param rectF 自訂hightlight 區
     * @param guidShape highLight shape
     * @round corner radious
     * @param padding 邊距
     * @param relativeGuide 關聯highlight區
     * @param options highligth event
     */
    fun addHighLight(rectF: RectF,guidShape: HighLight.GuidShape,round:Float,relativeGuide:RelativeGuide?,options: HighLightOptions?):GuidePage{
        val highLightRectF = HighlightRectF(rectF, guidShape, round,options)
        if (relativeGuide != null){
            TODO("RelativeGuide do some thing")
        }

        if (options != null){
            if(options.relativeGuide != null){
                options.relativeGuide!!.highlight = highLightRectF
            }
            highLightRectF.setOptions(options)
        }
        hightLightPages.add(highLightRectF)
        return this
    }




    fun getHightLightPages(): MutableList<HighLight> {
        return this.hightLightPages
    }



    fun isEveryWhereCancelbale():Boolean{
        return everyWhereCanCancle
    }


     fun setEveryWhereCancelable(cancelable: Boolean):GuidePage{
        this.everyWhereCanCancle = cancelable
        return this
    }

    @JvmOverloads
    fun setLayoutRes(layoutId:Int = 0, id: Array<Int>? = null):GuidePage{
        this.layoutId = layoutId
        this.clickToDismissId = id
        return this
    }


    fun getLayoutResId():Int{
        return layoutId
    }

    fun getClickDismissIds(): Array<Int>?{
        return clickToDismissId
    }


    fun getInflaterListener(): OnLayoutInflatedListener? {
        return inflaterListener
    }

    fun setInflaterListener(onLayoutInflatedListener: OnLayoutInflatedListener):GuidePage{
        this.inflaterListener = onLayoutInflatedListener
        return this
    }



    fun getRelativeGuides(): List<RelativeGuide> {
        val relativeGuides = ArrayList<RelativeGuide>()
        for (highLight in hightLightPages) {
            val options = highLight.getOptions()
            if (options != null) {
                if (options.relativeGuide != null) {
                    relativeGuides.add(options.relativeGuide!!)
                }
            }
        }
        return relativeGuides
    }


}