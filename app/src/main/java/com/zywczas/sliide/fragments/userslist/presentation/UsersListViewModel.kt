package com.zywczas.sliide.fragments.userslist.presentation

import androidx.lifecycle.viewModelScope
import com.zywczas.sliide.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.sliide.fragments.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : BaseViewModel() {

    fun getUsers() {
        viewModelScope.launch(dispatcherIO){

        }
    }

}