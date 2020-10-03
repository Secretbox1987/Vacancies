package com.example.mobile_ui.vacancy_list

import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import co.popov.mobile_ui.R

class DividerItemDecorator() : RecyclerView.ItemDecoration() {

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight

        val devider = ContextCompat.getDrawable(parent.context, R.drawable.devider) ?: return

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + devider.intrinsicHeight

            devider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            devider.draw(canvas)
        }
    }
}