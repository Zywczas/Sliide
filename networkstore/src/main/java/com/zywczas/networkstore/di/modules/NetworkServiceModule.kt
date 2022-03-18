package com.zywczas.networkstore.di.modules

import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.users.networkservice.UsersNetworkServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkServiceModule {

    @Binds
    internal abstract fun bindUsersNetworkService(service: UsersNetworkServiceImpl): UsersNetworkService

}