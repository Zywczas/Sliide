package com.zywczas.sliide.extentions

import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.onTextChangedClearError(parent: TextInputLayout) {
    doOnTextChanged { _, _, _, _ ->
        parent.error = null
    }
}