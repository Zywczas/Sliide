package com.zywczas.sliide.di.qualifiers

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey (val value: KClass<out ViewModel>)