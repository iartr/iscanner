# iscanner
//<!builds>  
  
An android library for scanning QR codes and barcodes with simple implementation  
Demo:   
![screen](https://i.ibb.co/qYSFFRt/screen.png)  
![gif](https://media.giphy.com/media/lSbY5PDIvvBHNLfzg7/giphy.gif)  
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/_9VXsXBoKn4/0.jpg)](https://www.youtube.com/watch?v=_9VXsXBoKn4 )  
  
## Implementation with gradle
  
  
## Quick start
1. First of all, add to your layout ScannableCamera  
<<>>  
  
1.1 If you need overlay, you can add ScannerOverlay  
<<>>  
  
2. Then override onScanned callback:  
Kotlin:  
Java:  
  
## Don't forget
1. Add permission to manifest:  
<<>>  
2. Before open fragment/activity with ScannableCamera, you must handle permission  
<<>>  
Pay attention: min api version is 21 (Android 5.0)  

## Roadmap