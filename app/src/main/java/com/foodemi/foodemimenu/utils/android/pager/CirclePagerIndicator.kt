package com.foodemi.foodemimenu.utils.android.pager

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.R

class CirclePagerIndicator : RecyclerView.ItemDecoration() {

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private val mIndicatorHeight = (DP * 8).toInt()

    /**
     * Indicator stroke width.
     */
    private val mIndicatorStrokeWidth = DP * 4

    /**
     * Indicator width.
     */
    private val mIndicatorItemLength = DP * 4
    /**
     * Padding between indicators.
     */
    private val mIndicatorItemPadding = DP * 8

    /**
     * Some more natural animation interpolation
     */
    private val mInterpolator = AccelerateDecelerateInterpolator()

    private val mPaint = Paint()

    var realItemCount = 0

    init {

        mPaint.strokeWidth = mIndicatorStrokeWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val indicatorStartX = parent.context.resources.getDimensionPixelSize(R.dimen._12sdp).toFloat()

        val indicatorPosY = parent.height - mIndicatorHeight / 2f

        drawInactiveIndicators(parent.context, c, indicatorStartX, indicatorPosY)
        val layoutManager = parent.layoutManager as LinearLayoutManager?
        var activePosition = layoutManager!!.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }
        val activeChild = layoutManager.findViewByPosition(activePosition)
        val left = activeChild!!.left
        val width = activeChild.width
        val right = activeChild.right

        val progress = when (activePosition) {
            1 -> 0f
            0 -> 0f
            realItemCount + 1 -> 0f
            realItemCount + 2 -> 0f
            else -> mInterpolator.getInterpolation(left * -1 / width.toFloat())
        }
        when (activePosition) {
            1 -> activePosition = realItemCount
            0 -> activePosition = realItemCount - 1
            realItemCount + 2 -> activePosition = 2
            else -> activePosition -= 1
        }

        drawHighlights(parent.context, c, indicatorStartX, indicatorPosY, activePosition, 0f)
    }

    private fun drawInactiveIndicators(context: Context, c: Canvas, indicatorStartX: Float, indicatorPosY: Float) {
        mPaint.color = ContextCompat.getColor(context, R.color.FOODEMI_COLOR_STEPPER_LINE)
        // width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding

        var start = indicatorStartX + itemWidth
        for (i in 0 until realItemCount) {
            c.drawCircle(start, indicatorPosY, mIndicatorItemLength / 2f, mPaint)
            start += itemWidth
        }
    }

    private fun drawHighlights(context: Context, c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
                               highlightPosition: Int, progress: Float) {
        mPaint.color = ContextCompat.getColor(context, R.color.FOODEMI_COLOR_STEPPER_POSITIVE)

        // width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding

        if (progress == 0f) {
            val highlightStart = indicatorStartX + itemWidth * highlightPosition

            c.drawCircle(highlightStart, indicatorPosY, mIndicatorItemLength / 2f, mPaint)

        } else {
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            val partialLength = mIndicatorItemLength * progress + mIndicatorItemPadding * progress

            c.drawCircle(highlightStart + partialLength, indicatorPosY, mIndicatorItemLength / 2f, mPaint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mIndicatorHeight
    }

    companion object {

        private val DP = Resources.getSystem().displayMetrics.density
    }
}