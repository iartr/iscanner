package com.iscanner.iscanner

import android.content.res.Resources
import android.util.SparseArray
import android.util.TypedValue
import androidx.core.util.forEach

fun <T> SparseArray<T>.toList(): List<T> {
    return ArrayList<T>().apply {
        this@toList.forEach { _, value -> add(value) }
    }
}

fun Int.dpToPx(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()
}