package com.zywczas.common.utils

import kotlin.reflect.KClass

interface Logger {

    fun <T : Any> logD(tag: KClass<T>, e: Throwable?)
    fun <T : Any> logD(tag: KClass<T>, msg: String?)

}