package com.app.ui.sidenavigation.home


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class RouteOverlay @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private var start: PointF? = null
    private var end: PointF? = null

    private val paint = Paint().apply {
        color = Color.RED
        strokeWidth = 8f
        style = Paint.Style.STROKE
        isAntiAlias = true
        pathEffect = DashPathEffect(floatArrayOf(20f, 10f), 0f) // 20px line, 10px space
    }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }



    fun drawRoute(start: PointF, end: PointF) {
        this.start = start
        this.end = end
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (start != null && end != null) {
            canvas.drawLine(start!!.x, start!!.y, end!!.x, end!!.y, paint)
            // Draw "Distance: 10 meters" above the midpoint of the line
//            val midX = (start!!.x + end!!.x) / 2
//            val midY = (start!!.y + end!!.y) / 2 - 20f // raise above the line
//            canvas.drawText("Distance: 10 meters", midX, midY, textPaint)
        }
    }
}
