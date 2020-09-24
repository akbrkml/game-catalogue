package com.badrun.core.utils.ext

import java.text.SimpleDateFormat
import java.util.*

fun String.formatted(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date)
}