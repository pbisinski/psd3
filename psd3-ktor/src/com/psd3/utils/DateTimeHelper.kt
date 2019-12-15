package com.psd3.utils

import org.joda.time.DateTime

const val STANDARD_FORMATTING = "yyyy-MM-dd'T'HH:mm:ssZ"

fun DateTime.toFormattedString(pattern: String = STANDARD_FORMATTING): String =
    this.toString(pattern)
