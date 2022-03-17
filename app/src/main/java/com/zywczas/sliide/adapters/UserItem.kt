package com.zywczas.sliide.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.sliide.R
import com.zywczas.sliide.databinding.ItemUserBinding

class UserItem : AbstractBindingItem<ItemUserBinding>() {

    override val type: Int = R.id.userItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemUserBinding =
        ItemUserBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemUserBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        //todo finish
        binding.name.text = "smt"
        binding.email.text = "email"
        binding.dateCreated.text = "date"
    }

}