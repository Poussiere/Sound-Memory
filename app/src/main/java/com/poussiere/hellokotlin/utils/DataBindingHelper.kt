package com.poussiere.hellokotlin.utils

import android.databinding.BindingConversion
import android.support.v4.content.ContextCompat
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