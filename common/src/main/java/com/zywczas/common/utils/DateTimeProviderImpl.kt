package com.zywczas.common.utils

import org.joda.time.DateTime
import javax.inject.Inject

class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {

    override fun now(): DateTime = DateTime.now()

}