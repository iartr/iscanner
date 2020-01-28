package com.iscanner.iscanner

import android.content.res.Resources
import android.util.SparseArray
import android.util.TypedValue
import androidx.core.util.forEach
import com.google.android.gms.vision.barcode.Barcode

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

fun List<Barcode>.hasBarcodeType(type: Int): Boolean {
    for (barcode in this) {
        if (barcode.format == type) {
            return true
        }
    }
    return false
}

fun Barcode.isQr(): Boolean {
    return this.format == Barcode.QR_CODE
}

// TODO: Add all extensions for Barcode.isType()