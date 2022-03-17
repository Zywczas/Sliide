package com.zywczas.sliide.fragments.userslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.zywczas.sliide.adapters.UserItem
import com.zywczas.sliide.databinding.FragmentUsersListBinding
import com.zywczas.sliide.di.factories.UniversalViewModelFactory
import com.zywczas.sliide.utils.autoRelease
import javax.inject.Inject

class UsersListFragment @Inject constructor(viewModelFactory: UniversalViewModelFactory) : Fragment() {

    private val viewModel: UsersListViewModel by viewModels { viewModelFactory }
    private var binding: FragmentUsersListBinding by autoRelease()
    private val userItemAdapter by lazy { ItemAdapter<UserItem>() }
    private val userFastAdapter by lazy { FastAdapter.with(userItemAdapter) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

}