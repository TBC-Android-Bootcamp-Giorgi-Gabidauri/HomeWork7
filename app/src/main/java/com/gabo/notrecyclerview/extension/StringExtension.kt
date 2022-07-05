package com.gabo.notrecyclerview.extension

fun String.addFieldsAsText(input: String): String {
    return if (this.isNotEmpty()) {
        this.plus("\n\nText:  $input")
    } else {
        this.plus("Text:  $input")
    }
}
fun String.addFieldsAsNumeric(input: String): String {
    return if (this.isNotEmpty()) {
        this.plus("\n\nNumeric: $input")
    } else {
        this.plus("Numeric: $input")
    }
}