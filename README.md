# iscanner
An android library for scanning QR codes and barcodes with simple implementation without confusing callbacks.  
Under the hood I use Google Mobile Vision API
   
Screen from demo applications:   
  
<img alt="demo screen" src="https://i.ibb.co/qYSFFRt/screen.png" width="400" height="680">
  
<img alt="demo screen" src="https://sun9-54.userapi.com/c205824/v205824023/536b8/wD1ylaIKuDU.jpg" width="400" height="680"> 
 
## Implementation with gradle
`implementation 'tech.iscanner:iscanner:1.2'`

## Quick start
1. First of all, add to your layout **ScannableCamera**  
```
<tech.iscanner.iscanner.ScannableCamera  
        android:id="@+id/scannableCamera"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        app:delayMillis="2000"  
        app:isActiveDelay="true" />
```        
  
2. If you need overlay, you can add **ScannerOverlay**  
```
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
```
scannableCamera.onScanned(object : ScannableCamera.OnScanned {
    override fun onScanned(barcodes: List<Barcode>) {
        changeFragment(ResultFragment()) 
    }
})
```  
      
**Java**:
```
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
> Pay attention: min api version is 21 (Android 5.0)

## Permission handling
Since 1.2 version you can easily handle permission. See sample:
```
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

## Roadmap
• **Add instrumental tests**  
• **Add java docs and documentation**  
• **Change facing feature**  
• Focused camera by tap  
• Take screenshots  
• Add zooming for camera  
• Reduce Android API  
• Create QR/bar codes  
• Add beep sound feature  
• Realize Camera2 API / CameraX

## How to contribute
Just fork this project and send pull request

## Find bug, have question or advice?
Use [issue section](https://github.com/32xlevel/iscanner/issues). I will answer you quickly as possible

## Changelog
See Changelog.md

## LICENSE
Copyright, 2020, Artur Ilkaev

   Licensed under the Apache License, Version 2.0 (the "License");  
   you may not use this file except in compliance with the License.  
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software  
   distributed under the License is distributed on an "AS IS" BASIS,  
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
   See the License for the specific language governing permissions and  
   limitations under the License.