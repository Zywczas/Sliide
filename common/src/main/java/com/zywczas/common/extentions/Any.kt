package com.zywczas.common.extentions

import android.util.Log
import kotlin.reflect.KClass

fun Any.logD(e: Throwable?) = logD(e?.message)

fun Any.logD(msg: String?) = Log.d("SliideTag in ${this.javaClass.simpleName}", "$msg")

fun <T : Any> Any.logD(tag: KClass<T>, e: Throwable?) = logD(tag, e?.message)

fun <T : Any> Any.logD(tag: KClass<T>, msg: String?) = Log.d("SliideTag in ${tag.simpleName}", "$msg")