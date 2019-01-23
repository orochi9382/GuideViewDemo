package com.lala.guidview.guid.core

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.lala.guidview.guid.listener.OnGuideLayoutDismissListener
import com.lala.guidview.guid.model.GuidePage
import com.lala.guidview.guid.model.HighLight
import java.util.*

class GuideLayout: FrameLayout {
    private var guideContrller:GuideContrller? = null
    private lateinit var mPaint:Paint
    private var guidpage:GuidePage? = null
    private var downX:Float = 0f
    private var downY:Float = 0f
    private var touchSlop:Int = 0
    private var mGuidLayouDismisstListener: OnGuideLayoutDismissListener? = null
    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context,guidePage: GuidePage,guideContrller: GuideContrller) : super(context){
        init()
        setGuidpage(guidePage)
        this.guideContrller = guideContrller
    }





    private fun setGuidpage(guidePage: GuidePage) {
       this.guidpage = guidePage
        setOnClickListener {
            dismiss()
        }
    }

    private fun dismiss() {
        if (parent != null){
                if(guidpage!!.isEveryWhereCancelbale())
                    (parent as ViewGroup).removeView(this)

            if (mGuidLayouDismisstListener != null){
                mGuidLayouDismisstListener!!.onGuideLayoutDismiss()
            }

        }
    }

    private fun init() {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        val xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        mPaint.xfermode = xfermode
        mPaint.maskFilter = BlurMaskFilter(10f,BlurMaskFilter.Blur.INNER)
        setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        setWillNotDraw(false)
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onFilterTouchEventForSecurity(event: MotionEvent?): Boolean {
        return super.onFilterTouchEventForSecurity(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.actionMasked

        when(action){
            MotionEvent.ACTION_DOWN->{
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_MOVE->{}

            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                val upX = event.x
                val upY = event.y
                if (Math.abs(upX - downX) < touchSlop && Math.abs(upY-downY) < touchSlop){
                    val hightLights = guidpage!!.getHightLightPages()
                    for (highlight in hightLights){
                        val rectF = highlight.getRectF(parent as ViewGroup)
                        if (rectF!!.contains(upX,upY)){
                           notifyClickListener(highlight)
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    fun notifyClickListener(hightLight: HighLight){
        val options = hightLight.getOptions()
        if (options != null){
            if (options.onClickListener != null){
                options.onClickListener!!.onClick(this)
            }
        }
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas!!)
        val backgroundColor = 0x4e000000 // default background color
        canvas.drawColor(backgroundColor)
        drawHightLights(canvas)
    }

    private fun drawHightLights(canvas:Canvas) {
        val hightLightPages = guidpage!!.getHightLightPages()
        if (guidpage != null){
            for (hightLight in hightLightPages){

                val rectF = hightLight.getRectF(parent as ViewGroup)

                when(hightLight.getShape()){
                    HighLight.GuidShape.CIRCLE ->{
                        canvas.drawCircle(rectF!!.centerX(),rectF.centerY(),hightLight.getRadius(),mPaint)
                    }
                    HighLight.GuidShape.OVAL ->{
                        canvas.drawOval(rectF,mPaint)
                    }
                    HighLight.GuidShape.ROUND_RECTANGLE ->{
                        canvas.drawRoundRect(rectF,hightLight.getRound(),hightLight.getRound(),mPaint)
                    }
                    else ->{
                        canvas.drawRect(rectF,mPaint)

                    }
                }
                notifyDrewListener(canvas, hightLight, rectF)
            }
        }

    }

    private fun notifyDrewListener(canvas: Canvas, highLight: HighLight, rectF: RectF?) {
        val options = highLight.getOptions()
        if (options != null){
            if (options.onHighLightDrawedListener != null){
                options.onHighLightDrawedListener!!.onHighLightDrawed(canvas,rectF!!)
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addCustomLayout()
    }



    fun  setOnGuideLayoutDismissListener(listener:OnGuideLayoutDismissListener){
        mGuidLayouDismisstListener = listener
    }


    fun addCustomLayout(){
        removeAllViews()
        val layoutID = guidpage!!.getLayoutResId()
        if (layoutID != 0){
            val view = LayoutInflater.from(context).inflate(layoutID,this,false)
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
             val inflaterListener = guidpage!!.getInflaterListener()
            if (inflaterListener != null){
                inflaterListener.onLayoutInflated(view,guideContrller!!)
            }
            addView(view,params)
        }



        val relativeGuides = guidpage!!.getRelativeGuides()
        if (relativeGuides.isNotEmpty()) {
            for (relativeGuide in relativeGuides) {
                addView(relativeGuide.getGuidLayout(parent as ViewGroup, guideContrller!!))
            }
        }
    }









}