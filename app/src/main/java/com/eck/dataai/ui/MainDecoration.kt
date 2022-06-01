package com.eck.dataai.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eck.dataai.R

class MainDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val margin = view.resources.getDimensionPixelOffset(R.dimen.half_margin)
        val isTop = parent.getChildLayoutPosition(view) == 0
        outRect.top = if (isTop) margin else 0
        outRect.bottom = margin
    }
}
