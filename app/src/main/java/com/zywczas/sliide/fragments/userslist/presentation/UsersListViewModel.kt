package com.zywczas.sliide.fragments.userslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zywczas.networkstore.utils.Resource
import com.zywczas.sliide.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.sliide.fragments.BaseViewModel
import com.zywczas.sliide.fragments.userslist.domain.User
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: UsersListRepository
) : BaseViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> = _usersList

    fun getUsers() {
        viewModelScope.launch(dispatcherIO){
            showProgressBar(true)
            val resource = repo.getUsersLastPage()
            if (resource is Resource.Success) {
                _usersList.postValue(resource.data ?: emptyList())
            } else {
                postMessage(resource.message)
            }
            showProgressBar(false)
        }
    }

}