package com.lala.guidview.guidsample

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.lala.guidview.guid.GuideManger
import com.lala.guidview.guid.core.GuideContrller
import com.lala.guidview.guid.core.GuideLayout
import com.lala.guidview.guid.listener.OnHighLightDrawedListener
import com.lala.guidview.guid.listener.OnLayoutInflatedListener
import com.lala.guidview.guid.model.GuidePage
import com.lala.guidview.guid.model.HighLight
import com.lala.guidview.guid.model.HighLightOptions
import com.lala.guidview.guid.model.RelativeGuide
import com.lala.guidview.guidsample.R.id.add
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt1.setOnClickListener {
            val contrller = GuideManger.with(this)
                .setLabel("normal")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(bt1))
            contrller.show()
        }





        bt2.setOnClickListener {


            val contrller = GuideManger.with(this)
                .setLabel("padding = 10 & round = 10 click")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(it,HighLight.GuidShape.CIRCLE))

            contrller.show()
        }

        bt3.setOnClickListener {
            val relativeGuide = RelativeGuide(R.layout.view_guid,Gravity.RIGHT,100,object :OnLayoutInflatedListener{
                override fun onLayoutInflated(view: View, contrller: GuideContrller) {

                }

            })


            val options = HighLightOptions.Build().setRelativeGuide(relativeGuide)
                .setOnClickListener(View.OnClickListener{
                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
            }).create()

            val contrller = GuideManger.with(this)
                .setLabel("click")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(bt3,HighLight.GuidShape.RECTANGLE,0,0f,options))

            contrller.show()
        }



        bt4.setOnClickListener {
            val options = HighLightOptions.Build().setOnClickListener(View.OnClickListener{
                Toast.makeText(this,"padding = 10 & round = 10 click",Toast.LENGTH_SHORT).show()
            }).create()

            val contrller = GuideManger.with(this)
                .setLabel("add layout")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(bt4,HighLight.GuidShape.RECTANGLE,20,20f,options)
                    .setLayoutRes(R.layout.view_guid)
                )

            contrller.show()
        }

        bt5.setOnClickListener {
            val options = HighLightOptions.Build().setOnHighLightDrawedListener(object :OnHighLightDrawedListener{
                override fun onHighLightDrawed(canvas: Canvas, rectF: RectF) {
                    val paint = Paint()
                    paint.color = Color.YELLOW
                    paint.style = Paint.Style.STROKE
                    paint.strokeWidth = 10f
                    canvas.drawCircle(rectF.centerX(),rectF.centerY(),rectF.width()/2+10,paint)
                }

            }).create()

            val contrller = GuideManger.with(this)
                .setLabel("custom layout")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(bt5,options)

                )

            contrller.show()
        }


        bt6.setOnClickListener {

            val rect = RectF(500f,100f,300f,400f)
            val contrller = GuideManger.with(this)
                .setLabel("any where")
                .alwaysShow(true)
                .addGuidePage(GuidePage.newInstance()
                    .addHighLight(rect)

                )

            contrller.show()
        }



        bt7.setOnClickListener {

            startActivity(Intent(this,AAA::class.java))
        }


    }

}
