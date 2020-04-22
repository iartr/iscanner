# iscanner
An android library for scanning QR codes and barcodes with simple implementation without confusing callbacks.  
Under the hood it uses Google Mobile Vision API

**Attention**: Development on this library is winding down. Please consider switching to [cameraX](https://developer.android.com/training/camerax) from JetPack
   
Screen from demo applications:   

<div style="display: flex; justify-content: space-between">
    <img src="https://i.ibb.co/sjKGcsX/demoscreen.png" alt="demo screen" width="400" height="675">
    <img alt="demo screen" src="https://sun9-54.userapi.com/c205824/v205824023/536b8/wD1ylaIKuDU.jpg" width="400" height="680">
</div>
 
## Implementation with gradle
`implementation 'tech.iscanner:iscanner:1.3'`

## Quick start
1. First of all, add to your layout **ScannableCamera**  
```xml
<tech.iscanner.iscanner.ScannableCamera  
        android:id="@+id/scannableCamera"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        app:delayMillis="2000"  
        app:isActiveDelay="true" />
```        
  
2. If you need overlay, you can add **ScannerOverlay**  
```xml
<tech.iscanner.iscanner.ScannerOverlay
        android:id="@+id/scannerOverlay"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/transparent_gray"
        app:cornersSize="25"
        app:cornersWidth="4"
        app:cornersColor="@android:color/white"
        app:squareHeight="200"
        app:squareWidth="312"
        app:squareTopMargin="230" />
```
  
3. Then override **onScanned callback**:  
**Kotlin**:  
```kotlin
scannableCamera.onScanned(object : ScannableCamera.OnScanned {
    override fun onScanned(barcodes: List<Barcode>) {
        changeFragment(ResultFragment()) 
    }
})
```  
      
**Java**:
```java
scannableCamera.onScanned(new ScannableCamera.OnScanned() {
    @Override
    public void onScanned(@NotNull List<? extends Barcode> barcodes) {
         changeFragment(ResultFragment())       
    }
});
```

4. **Additional functions**:  
• Flash on/off: `scannableCamera.flash(isFlash)`. Throws FlashException if flash in not supported.  
• Change facing: `scannableCamera.changeFacing(isBackFacing)`  
• `boolean barcodesList.hasBarcodeType(Barcode.YOUR_TYPE)`  
• Check barcode type: `barcode.isQr()`, `barcode.isEan13()`, `barcode.isVkQr()` and others  
• Check barcode value format: `barcode.isText()`, `barcode.isWifi()`, `barcode.isGeo()`, `barcode.isCalendarEvent()`, `barcode.isDriverLicence()`, `barcode.isUrl()`, `barcode.isContactInfo()`, `barcode.isEmail()`, `barcode.isPhone()`, `barcode.isIsbn()`, `barcode.isProduct()`, `barcode.isSms()`  

## Don't forget
1. Add permission to **manifest**:  
`<uses-permission android:name="android.permission.CAMERA" />`
2. Before open fragment/activity with ScannableCamera, you must [handle](https://developer.android.com/training/permissions/requesting) permission    
> Min api version is 16

## Permission handling
Since 1.2 version you can easily handle permission. See sample:
```kotlin
// In your fragment / activity with camera:
if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {  
    requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
} else {
    // It is handling in library. Do not nothing with camera source
}

override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    if (requestCode == CAMERA_REQUEST) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            scannableCamera.startCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
        }
    }
}
```

## How to contribute
Just fork this project and send pull request

## Find bug, have question or advice?
Use [issue section](https://github.com/32xlevel/iscanner/issues). I will answer you quickly as possible

## Changelog
See [Changelog](Changelog.md).

## LICENSE
```
Copyright 2020 Artur Ilkaev

Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
```