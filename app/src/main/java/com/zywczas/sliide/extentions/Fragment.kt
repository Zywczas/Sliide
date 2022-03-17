package com.zywczas.sliide.extentions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(@StringRes msg: Int) = Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG).show()