package com.zywczas.common.di.modules

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.common.utils.DateTimeProviderImpl
import com.zywczas.common.utils.Logger
import com.zywczas.common.utils.LoggerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CommonBinderModule {

    @Binds
    abstract fun bindDateTimeProvider(util: DateTimeProviderImpl): DateTimeProvider

    @Binds
    abstract fun bindLogger(util: LoggerImpl): Logger

}