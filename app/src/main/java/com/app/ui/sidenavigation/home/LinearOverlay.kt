package com.app.ui.sidenavigation.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class LineOverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var start: PointF? = null
    private var end: PointF? = null

    private val paint = Paint().apply {
        color = 0xFF007AFF.toInt() // Blue
        strokeWidth = 8f
        isAntiAlias = true
    }

    fun drawLineBetweenPoints(start: PointF, end: PointF) {
        this.start = start
        this.end = end
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (start != null && end != null) {
            canvas.drawLine(start!!.x, start!!.y, end!!.x, end!!.y, paint)
        }
    }
}
