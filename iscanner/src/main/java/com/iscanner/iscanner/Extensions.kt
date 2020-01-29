package com.iscanner.iscanner

import com.google.android.gms.vision.barcode.Barcode

fun List<Barcode>.hasBarcodeType(type: Int): Boolean {
    for (barcode in this) {
        if (barcode.format == type) {
            return true
        }
    }
    return false
}

// For VK QR codes it is EAN_13
fun Barcode.isQr(): Boolean {
    return this.format == Barcode.QR_CODE
}

// TODO: Add all extensions for Barcode.isType()