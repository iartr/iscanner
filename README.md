# iscanner
An android library for scanning QR codes and barcodes with simple implementation without confusing callbacks.  
Under the hood I use Google Mobile Vision API
   
Screen from demo applications:   
  
<img alt="demo screen" src="https://i.ibb.co/qYSFFRt/screen.png" width="400" height="680">
  
<img alt="demo screen" src="https://sun9-54.userapi.com/c205824/v205824023/536b8/wD1ylaIKuDU.jpg" width="400" height="680"> 
 
## Implementation with gradle
`implementation 'tech.iscanner:iscanner:1.1'`

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
• `boolean barcodesList.hasBarcodeType(Barcode.YOUR_TYPE)`  
• Check barcode type: `barcode.isQr()`, `barcode.isEan13()`, `barcode.isVkQr()` and others  
• Check barcode value format: `barcode.isText()`, `barcode.isWifi()`, `barcode.isGeo()`, `barcode.isCalendarEvent()`, `barcode.isDriverLicence()`, `barcode.isUrl()`, `barcode.isContactInfo()`, `barcode.isEmail()`, `barcode.isPhone()`, `barcode.isIsbn()`, `barcode.isProduct()`, `barcode.isSms()`  

## Don't forget
1. Add permission to **manifest**:  
`<uses-permission android:name="android.permission.CAMERA" />`
2. Before open fragment/activity with ScannableCamera, you must [handle](https://developer.android.com/training/permissions/requesting) permission    
> Pay attention: min api version is 21 (Android 5.0)  

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
The Apache Software License, Version 2.0