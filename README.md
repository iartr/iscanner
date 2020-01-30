# iscanner
//<!builds>  
  
An android library for scanning QR codes and barcodes with simple implementation 
   
Screen from demo application:   
  
<img alt="demo screen" src="https://i.ibb.co/qYSFFRt/screen.png" width="360" height="640">  
  
## Implementation with gradle
`implementation 'tech.iscanner.iscanner:1.0'`

## Quick start
1. First of all, add to your layout **ScannableCamera**  
`<com.iscanner.iscanner.ScannableCamera  
        android:id="@+id/scannableCamera"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        app:delayMillis="2000"  
        app:isActiveDelay="true" />`
  
2. If you need overlay, you can add **ScannerOverlay**  
`<com.iscanner.iscanner.ScannerOverlay
        android:id="@+id/scannerOverlay"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:background="@color/gray"
        app:cornersSize="25"
        app:cornersWidth="4"
        app:cornersColor="@android:color/white"
        app:squareHeight="200"
        app:squareWidth="312"
        app:squareTopMargin="230" /> `
  
3. Then override **onScanned callback**:  
**Kotlin**:  
`scannableCamera.onScanned { barcodes: List<Barcode> ->  
    changeFragment(ResultFragment())  
}`  
      
**Java** (this callback will be changed by more friendly, see **Roadmap**):  
`scannableCamera.onScanned(new Function1<List<? extends Barcode>, Unit>() {
    @Override
    public Unit invoke(List<? extends Barcode> barcodes) {
        changeFragment(new ResultFragment());
        return null;
    }
});  `

4. **Additional functions**:  
• `boolean barcodes.hasBarcodeType(Barcode.YOUR_TYPE)`  
• `barcode.isQr()`, `barcode.isEan13()`, `barcode.isVkQr()` and others  

## Don't forget
1. Add permission to **manifest**:  
`<uses-permission android:name="android.permission.CAMERA" />`
2. Before open fragment/activity with ScannableCamera, you must [handle](https://developer.android.com/training/permissions/requesting) permission    
> Pay attention: min api version is 21 (Android 5.0)  

## Roadmap
• Add extension functions for each barcode type  
• Add unit and instrumental tests  
• Add java docs  
• Add change facing feature  
• Redraw views if field was changed  
• Focused camera by tap  
• Take screenshots  
• Add zooming for camera  
• Reduce Android API  
• For java make override callback more beautiful  
• Create QR/bar codes  
• Realize Camera2 API  

## How to contribute

## Find bug, have question or advice?