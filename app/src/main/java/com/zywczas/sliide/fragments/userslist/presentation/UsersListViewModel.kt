package com.zywczas.sliide.fragments.userslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zywczas.sliide.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.sliide.fragments.BaseViewModel
import com.zywczas.sliide.fragments.userslist.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : BaseViewModel() {

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>> = _usersList

    fun getUsers() {
        viewModelScope.launch(dispatcherIO){
            showProgressBar(true)
            val list = listOf(
                User("Piotr", "p1@o2.pl"),
                User("Michal", "p2@o2.pl"),
                User("Piotr", "p3@o2.pl"),
                User("Michal", "p4@o2.pl"),
                User("Piotr", "p1552.pl"),
                User("Michal", "p662.pl"),
                User("Piotr", "p1@o2.pl"),
                User("Michal", "p2@o2.pl"),
                User("Piotr", "p3@o2.pl"),
                User("Michal", "p4@o2.pl"),
                User("Piotr", "p1552.pl"),
                User("Michal", "p662.pl"),
                User("Piotr", "p1@o2.pl"),
                User("Michal", "p2@o2.pl"),
                User("Piotr", "p3@o2.pl"),
                User("Michal", "p4@o2.pl"),
                User("Piotr", "p1552.pl"),
                User("Michal", "p662.pl"),
            )
            _usersList.postValue(list)
            showProgressBar(false)
        }
    }

}