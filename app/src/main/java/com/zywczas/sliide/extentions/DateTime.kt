package com.zywczas.sliide.extentions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.dayFormat(): String = DateTimeFormat.forPattern("yyyy-MM-dd").print(this)