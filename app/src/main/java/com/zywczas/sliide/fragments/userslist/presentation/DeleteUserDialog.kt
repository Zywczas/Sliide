package com.zywczas.sliide.fragments.userslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.zywczas.sliide.R
import com.zywczas.sliide.databinding.DialogDeleteUserBinding
import com.zywczas.sliide.fragments.userslist.domain.User
import com.zywczas.sliide.utils.autoRelease

class DeleteUserDialog : DialogFragment() {

    private val viewModel: UsersListViewModel by viewModels({ requireParentFragment() })
    private var binding: DialogDeleteUserBinding by autoRelease()
    private val user: User by lazy { requireArguments().getParcelable(UsersListFragment.KEY_USER) ?: User() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogDeleteUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.message.text = getString(R.string.are_you_sure_delete_user, user.name)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners(){
        binding.cancel.setOnClickListener { dismiss() }
        binding.confirm.setOnClickListener {
            viewModel.deleteUser(user.id)
            dismiss()
        }
    }

}