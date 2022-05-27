package com.eck.dataai.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eck.dataai.R

@BindingAdapter("android:visibility")
fun View.bindVisibility(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter("status")
fun setStatus(viewGroup: ViewGroup, status: Boolean) {
    val resId = if (status) R.drawable.bg_blue_light_rounded_10dp else R.drawable.bg_gray_rounded_10dp
    viewGroup.setBackgroundResource(resId)
}

@BindingAdapter("itemViewModels")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter(value = ["category", "publisher"])
fun setCategoryAndPublisher(view: TextView, category: String, publisher: String) {
    view.text = String.format("%s | %s", category, publisher)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): BindableRecyclerViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
        recyclerView.adapter as BindableRecyclerViewAdapter
    } else {
        val bindableRecyclerAdapter = BindableRecyclerViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}
