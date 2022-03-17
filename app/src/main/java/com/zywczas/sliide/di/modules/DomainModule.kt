package com.zywczas.sliide.di.modules

import com.zywczas.sliide.SessionManager
import com.zywczas.sliide.SessionManagerImpl
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepository
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindSessionManager(manager: SessionManagerImpl): SessionManager

    @Binds
    abstract fun bindUsersListRepository(repo: UsersListRepositoryImpl): UsersListRepository

}