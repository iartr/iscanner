package tech.iscanner.iscanner

import com.google.android.gms.vision.barcode.Barcode

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

fun Barcode.isVkQr(): Boolean {
    return isEan13()
}

fun Barcode.isCode128(): Boolean {
    return this.format == Barcode.CODE_128
}

fun Barcode.isCode39(): Boolean {
    return this.format == Barcode.CODE_39
}

fun Barcode.isCode93(): Boolean {
    return this.format == Barcode.CODE_93
}

fun Barcode.isCodabar(): Boolean {
    return this.format == Barcode.CODABAR
}

fun Barcode.isDataMatrix(): Boolean {
    return this.format == Barcode.DATA_MATRIX
}

fun Barcode.isEan13(): Boolean {
    return this.format == Barcode.EAN_13
}

fun Barcode.isEan8(): Boolean {
    return this.format == Barcode.EAN_8
}

fun Barcode.isItf(): Boolean {
    return this.format == Barcode.ITF
}

fun Barcode.isUpcA(): Boolean {
    return this.format == Barcode.UPC_A
}

fun Barcode.isUpcE(): Boolean {
    return this.format == Barcode.UPC_E
}

fun Barcode.isPdf417(): Boolean {
    return this.format == Barcode.PDF417
}

fun Barcode.isAztec(): Boolean {
    return this.format == Barcode.AZTEC
}

fun Barcode.isContactInfo(): Boolean {
    return this.valueFormat == Barcode.CONTACT_INFO
}

fun Barcode.isEmail(): Boolean {
    return this.valueFormat == Barcode.EMAIL
}

fun Barcode.isIsbn(): Boolean {
    return this.valueFormat == Barcode.ISBN
}

fun Barcode.isPhone(): Boolean {
    return this.valueFormat == Barcode.PHONE
}

fun Barcode.isProduct(): Boolean {
    return this.valueFormat == Barcode.PRODUCT
}

fun Barcode.isSms(): Boolean {
    return this.valueFormat == Barcode.SMS
}

fun Barcode.isUrl(): Boolean {
    return this.valueFormat == Barcode.URL
}

fun Barcode.isWifi(): Boolean {
    return this.valueFormat == Barcode.WIFI
}

fun Barcode.isGeo(): Boolean {
    return this.valueFormat == Barcode.GEO
}

fun Barcode.isCalendarEvent(): Boolean {
    return this.valueFormat == Barcode.CALENDAR_EVENT
}

fun Barcode.isDriverLicence(): Boolean {
    return this.valueFormat == Barcode.DRIVER_LICENSE
}

fun Barcode.isTextCode(): Boolean {
    return this.valueFormat == Barcode.TEXT
}

