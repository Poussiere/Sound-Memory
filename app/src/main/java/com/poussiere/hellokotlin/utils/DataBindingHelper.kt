package com.poussiere.hellokotlin.utils

import androidx.databinding.BindingConversion
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.poussiere.hellokotlin.R

/**
 * Boolean to visibility converter
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.INVISIBLE
}