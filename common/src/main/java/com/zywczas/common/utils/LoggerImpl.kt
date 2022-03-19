package com.zywczas.common.utils

import android.util.Log
import javax.inject.Inject
import kotlin.reflect.KClass

class LoggerImpl @Inject constructor() : Logger {

    override fun <T : Any> logD(tag: KClass<T>, e: Throwable?) {
        logD(tag, e?.message)
    }

    override fun <T : Any> logD(tag: KClass<T>, msg: String?) {
        Log.d("SliideTag in ${tag.simpleName}", "$msg")
    }

}