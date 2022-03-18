package com.zywczas.common.extentions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.dayAndTimeFormat(): String = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").print(this)