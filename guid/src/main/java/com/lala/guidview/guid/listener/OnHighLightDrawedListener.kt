package com.lala.guidview.guid.listener

import android.graphics.Canvas
import android.graphics.RectF

interface OnHighLightDrawedListener {
    fun onHighLightDrawed(canvas: Canvas,rectF: RectF)
}