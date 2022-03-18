package com.zywczas.sliide.fragments.userslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.zywczas.common.extentions.logD
import com.zywczas.sliide.adapters.DiffUtilCallback
import com.zywczas.sliide.adapters.UserItem
import com.zywczas.sliide.databinding.FragmentUsersListBinding
import com.zywczas.sliide.di.factories.UniversalViewModelFactory
import com.zywczas.sliide.extentions.addVerticalItemDivider
import com.zywczas.sliide.extentions.showSnackbar
import com.zywczas.sliide.fragments.userslist.domain.User
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
        binding.usersList.setup()
        setupObservers()
        setupOnClickListeners()
    }

    private fun RecyclerView.setup() {
        adapter = userFastAdapter
        addVerticalItemDivider()
    }

    private fun setupObservers() {
        viewModel.message.observe(viewLifecycleOwner) { showSnackbar(it) }
        viewModel.isProgressBarVisible.observe(viewLifecycleOwner) { binding.progressBar.isVisible = it }
        viewModel.usersList.observe(viewLifecycleOwner) { FastAdapterDiffUtil.set(userItemAdapter, it.toGenericItems(), DiffUtilCallback()) }
    }

    private fun List<User>.toGenericItems(): List<UserItem> = map { UserItem(it) }

    private fun setupOnClickListeners(){
        userFastAdapter.onLongClickListener = { _, _, item: UserItem, _ ->
            logD(item.user.name)
            true
        }
        binding.fab.setOnClickListener {
            //todo
        }
    }

}