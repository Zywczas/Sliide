package com.zywczas.sliide.fragments.userslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.zywczas.sliide.R
import com.zywczas.sliide.databinding.DialogAddUserBinding
import com.zywczas.sliide.extentions.onTextChangedClearError
import com.zywczas.sliide.utils.autoRelease

class CreateUserDialog : DialogFragment() {

    private val viewModel: UsersListViewModel by viewModels({ requireParentFragment() })
    private var binding: DialogAddUserBinding by autoRelease()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        binding.name.onTextChangedClearError(binding.nameFrame)
        binding.email.onTextChangedClearError(binding.emailFrame)
    }

    private fun setupOnClickListeners(){
        binding.cancel.setOnClickListener { dismiss() }
        binding.confirm.setOnClickListener { createUser() }
    }

    private fun createUser() {
        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        if (name.isBlank()) binding.nameFrame.error = getString(R.string.name_required)
        if (email.isBlank()) binding.emailFrame.error = getString(R.string.email_required)
        if (name.isNotBlank() && email.isNotBlank()) {
            viewModel.createUser(name, email)
            dismiss()
        }
    }

}