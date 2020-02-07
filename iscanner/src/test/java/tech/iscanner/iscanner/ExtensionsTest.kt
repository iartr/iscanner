package tech.iscanner.iscanner

import com.google.android.gms.vision.barcode.Barcode
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionsTest {

    private companion object {
        private val QR_CODE = Barcode().apply { format = Barcode.QR_CODE }
        private val VK_QR_CODE = Barcode().apply { format = Barcode.EAN_13 }
        private val EAN13_CODE = Barcode().apply { format = Barcode.EAN_13 }
        private val EAN8_CODE = Barcode().apply { format = Barcode.EAN_8 }
        private val CODE128_CODE = Barcode().apply { format = Barcode.CODE_128 }
        private val CODE93_CODE = Barcode().apply { format = Barcode.CODE_93 }
        private val CODE39_CODE = Barcode().apply { format = Barcode.CODE_39 }
        private val CODABAR_CODE = Barcode().apply { format = Barcode.CODABAR }
        private val DATAMATRIX_CODE = Barcode().apply { format = Barcode.DATA_MATRIX }
        private val ITF_CODE = Barcode().apply { format = Barcode.ITF }
        private val UPC_A_CODE = Barcode().apply { format = Barcode.UPC_A }
        private val UPC_E_CODE = Barcode().apply { format = Barcode.UPC_E }
        private val PDF417_CODE = Barcode().apply { format = Barcode.PDF417 }
        private val AZTEC_CODE = Barcode().apply { format = Barcode.AZTEC }

        private val TEXT_CODE = Barcode().apply {
            displayValue = "Text"
            valueFormat = Barcode.TEXT
            contactInfo = Barcode.ContactInfo().apply {
                organization = "iscanner.tech"
            }
        }

        private val CONTACT_CODE = Barcode().apply {
            valueFormat = Barcode.CONTACT_INFO
            contactInfo = Barcode.ContactInfo().apply {
                organization = "iscanner.tech"
            }
        }

        private val EMAIL_CODE = Barcode().apply {
            valueFormat = Barcode.EMAIL
            email = Barcode.Email().apply {
                body = "ilkaev.artur"
            }
        }

        private val PHONE_CODE = Barcode().apply {
            valueFormat = Barcode.PHONE
            phone = Barcode.Phone().apply {
                number = "xxx-xxx-xxx"
            }
        }

        private val PRODUCT_CODE = Barcode().apply {
            valueFormat = Barcode.PRODUCT
        }

        private val SMS_CODE = Barcode().apply {
            valueFormat = Barcode.SMS
            sms = Barcode.Sms().apply {
                message = "I'm waiting for jobs offers"
                phoneNumber = "xxx-xxx-xxx"
            }
        }

        private val URL_CODE = Barcode().apply {
            valueFormat = Barcode.URL
            url = Barcode.UrlBookmark().apply {
                title = "The best barcode scanner library"
                url = "http://iscanner.tech"
            }
        }

        private val WIFI_CODE = Barcode().apply {
            valueFormat = Barcode.WIFI
            wifi = Barcode.WiFi().apply {
                password = "123456789"
            }
        }

        private val GEO_CODE = Barcode().apply {
            valueFormat = Barcode.GEO
            geoPoint = Barcode.GeoPoint().apply {
                lat = 5.0
                lng = -5.0
            }
        }

        private val CALENDAR_EVENT_CODE = Barcode().apply {
            valueFormat = Barcode.CALENDAR_EVENT
            calendarEvent = Barcode.CalendarEvent().apply {
                description = "description"
            }
        }

        private val DRIVER_LICENSE_CODE = Barcode().apply {
            valueFormat = Barcode.DRIVER_LICENSE
            driverLicense = Barcode.DriverLicense().apply {
                firstName = "Artur"
                lastName = "Ilkaev"
            }
        }

        private val ISBN_CODE = Barcode().apply {
            valueFormat = Barcode.ISBN
        }
    }

    @Test
    fun isQrTest() {
        assertTrue(QR_CODE.isQr())
    }

    @Test
    fun isVkQrTest() {
        assertTrue(VK_QR_CODE.isVkQr())
    }

    @Test
    fun isEan13Test() {
        assertTrue(EAN13_CODE.isEan13())
    }

    @Test
    fun isEan8Test() {
        assertTrue(EAN8_CODE.isEan8())
    }

    @Test
    fun isCode128Test() {
        assertTrue(CODE128_CODE.isCode128())
    }

    @Test
    fun isCode93Test() {
        assertTrue(CODE93_CODE.isCode93())
    }

    @Test
    fun isCode39Test() {
        assertTrue(CODE39_CODE.isCode39())
    }

    @Test
    fun isCodabarTest() {
        assertTrue(CODABAR_CODE.isCodabar())
    }

    @Test
    fun isDatamatrixTest() {
        assertTrue(DATAMATRIX_CODE.isDataMatrix())
    }

    @Test
    fun isItfTest() {
        assertTrue(ITF_CODE.isItf())
    }

    @Test
    fun isUPCATest() {
        assertTrue(UPC_A_CODE.isUpcA())
    }

    @Test
    fun isUPCETest() {
        assertTrue(UPC_E_CODE.isUpcE())
    }

    @Test
    fun isPdf417Test() {
        assertTrue(PDF417_CODE.isPdf417())
    }

    @Test
    fun isAztecTest() {
        assertTrue(AZTEC_CODE.isAztec())
    }

    @Test
    fun isContactInfoCodeTest() {
        assertTrue(CONTACT_CODE.isContactInfo())
    }

    @Test
    fun isEmailCodeTest() {
        assertTrue(EMAIL_CODE.isEmail())
    }

    @Test
    fun isPhoneTest() {
        assertTrue(PHONE_CODE.isPhone())
    }

    @Test
    fun isProductTest() {
        assertTrue(PRODUCT_CODE.isProduct())
    }

    @Test
    fun isSmsCodeTest() {
        assertTrue(SMS_CODE.isSms())
    }

    @Test
    fun isWifiCodeTest() {
        assertTrue(WIFI_CODE.isWifi())
    }

    @Test
    fun isTextCode() {
        assertTrue(TEXT_CODE.isTextCode())
    }

    @Test
    fun isGeoCodeTest() {
        assertTrue(GEO_CODE.isGeo())
    }

    @Test
    fun isCalendarEventTest() {
        assertTrue(CALENDAR_EVENT_CODE.isCalendarEvent())
    }

    @Test
    fun isDriverLicenseCodeTest() {
        assertTrue(DRIVER_LICENSE_CODE.isDriverLicence())
    }

    @Test
    fun isUrlCodeTest() {
        assertTrue(URL_CODE.isUrl())
    }

    @Test
    fun isIsbnCode() {
        assertTrue(ISBN_CODE.isIsbn())
    }
}