package com.zywczas.common.utils

import org.joda.time.DateTime

interface DateTimeProvider {

    fun now(): DateTime

}