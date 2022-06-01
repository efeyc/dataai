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
import com.eck.dataai.models.api.RevenueData
import com.eck.dataai.models.api.UnitsData

@BindingAdapter("android:visibility")
fun View.bindVisibility(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter("status")
fun setStatus(viewGroup: ViewGroup, status: Boolean) {
    val resId = if (status) R.drawable.bg_blue_light_rounded_10dp else R.drawable.bg_gray_rounded_10dp
    viewGroup.isEnabled = status
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

@BindingAdapter("unitsData")
fun setUnitsDescription(view: TextView, unitsData: UnitsData) {
    val sb = StringBuilder()
    sb.append(view.resources.getString(R.string.units_description_downloads, unitsData.product.downloads))
    sb.append("\n")
    sb.append(view.resources.getString(R.string.units_description_updates, unitsData.product.updates))
    sb.append("\n")
    sb.append(view.resources.getString(R.string.units_description_sales, unitsData.iap.sales))
    view.text = sb.toString()
}

@BindingAdapter("revenueData")
fun setRevenueDescription(view: TextView, revenueData: RevenueData) {
    val sb = StringBuilder()
    sb.append(view.resources.getString(R.string.revenue_description_sales, revenueData.iap.sales.toString()))
    sb.append("\n")
    sb.append(view.resources.getString(R.string.revenue_description_refunds, revenueData.iap.refunds.toString()))
    view.text = sb.toString()
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
