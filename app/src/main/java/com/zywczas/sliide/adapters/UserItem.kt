package com.zywczas.sliide.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.zywczas.sliide.R
import com.zywczas.sliide.databinding.ItemUserBinding
import com.zywczas.common.extentions.dayAndTimeFormat
import com.zywczas.sliide.fragments.userslist.domain.User

class UserItem(val user: User) : AbstractBindingItem<ItemUserBinding>() {

    override val type: Int = R.id.userItem

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemUserBinding =
        ItemUserBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemUserBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.name.text = user.name
        binding.email.text = user.email
        binding.dateCreated.text = user.dateCreated.dayAndTimeFormat()
    }

}