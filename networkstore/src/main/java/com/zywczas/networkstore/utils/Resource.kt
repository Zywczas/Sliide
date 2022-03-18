package com.zywczas.networkstore.utils

import androidx.annotation.StringRes

sealed class Resource<T>(val data: T? = null, @StringRes val message: Int = android.R.string.unknownName) {

    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(@StringRes message: Int) : Resource<T>(message = message)

}