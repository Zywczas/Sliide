package com.zywczas.sliide.adapters

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.diff.DiffCallback

class DiffUtilCallback<GenericItem> : DiffCallback<GenericItem> {

    override fun areItemsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when {
            oldItem is UserItem && newItem is UserItem -> oldItem.user.id == newItem.user.id
            else -> false
        }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        when (oldItem) {
            is UserItem -> oldItem.user == (newItem as UserItem).user
            else -> false
        }

    override fun getChangePayload(oldItem: GenericItem, oldItemPosition: Int, newItem: GenericItem, newItemPosition: Int): Any = newItem as com.mikepenz.fastadapter.GenericItem

}