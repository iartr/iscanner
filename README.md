# iscanner
//<!builds>  
  
An android library for scanning QR codes and barcodes with simple implementation  
Demo:   
![screen](https://i.ibb.co/qYSFFRt/screen.png)  
![gif](https://media.giphy.com/media/lSbY5PDIvvBHNLfzg7/giphy.gif)  
  
## Implementation with gradle
  
  
## Quick start
1. First of all, add to your layout ScannableCamera  
<<>>  
  
1.1 If you need overlay, you can add ScannerOverlay  
<<>>  
  
2. Then override onScanned callback:  
Kotlin:  
Java:  

3. Additional functions:  
• barcodes.hasBarcodeType(Barcode.YOUR_TYPE) // List<Barcode> barcodes
• barcode.isQr(), barcode.isEan13() and others

## Don't forget
1. Add permission to manifest:  
<<>>  
2. Before open fragment/activity with ScannableCamera, you must handle permission  
<<>>  
Pay attention: min api version is 21 (Android 5.0)  

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