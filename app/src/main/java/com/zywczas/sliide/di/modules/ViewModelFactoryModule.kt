package com.zywczas.sliide.di.modules

import androidx.lifecycle.ViewModel
import com.zywczas.sliide.di.qualifiers.ViewModelKey
import com.zywczas.sliide.fragments.userslist.presentation.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindUsersListViewModel(vm: UsersListViewModel) : ViewModel

}