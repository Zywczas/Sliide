package com.zywczas.sliide.di

import android.app.Application
import com.zywczas.networkstore.di.modules.NetworkServiceModule
import com.zywczas.networkstore.di.modules.RetrofitModule
import com.zywczas.sliide.BaseApp
import com.zywczas.sliide.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DispatchersModule::class,
    DomainModule::class,
    ActivityBuilderModule::class,
    FragmentFactoryModule::class,
    ViewModelFactoryModule::class,
    RetrofitModule::class,
    NetworkServiceModule::class
])
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}