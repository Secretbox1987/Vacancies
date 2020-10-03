package com.example.presentation.extentions

import android.text.Html
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.escapeHtml(): String {
    return if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString();
    } else {
        Html.fromHtml(this).toString();
    }
}

fun String.formatDate(): String {
    val calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getTimeZone("UTC");
    calendar.time = Date(this)
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return sdf.format(calendar.time)
}

