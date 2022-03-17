package com.zywczas.sliide.extentions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.zywczas.sliide.R

fun RecyclerView.addVerticalItemDivider(){
    val itemDivider = DividerItemDecoration(context, RecyclerView.VERTICAL)
    ContextCompat.getDrawable(context, R.drawable.item_divider_vertical)?.let {
        itemDivider.setDrawable(it)
    }
    addItemDecoration(itemDivider)
}