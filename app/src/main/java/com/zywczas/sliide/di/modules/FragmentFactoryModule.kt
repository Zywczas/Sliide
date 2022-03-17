package com.zywczas.sliide.di.modules

import androidx.fragment.app.Fragment
import com.zywczas.sliide.di.qualifiers.FragmentKey
import com.zywczas.sliide.fragments.userslist.presentation.UsersListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(UsersListFragment::class)
    abstract fun bindUsersListFragment(fragment: UsersListFragment): Fragment

}