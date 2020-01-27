package com.iscanner.iscanner

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.util.isEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class ScannableCamera @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArr: Int = 0
) : SurfaceView(context, attrs, defStyleArr) {

    private var onScannedCallback: ((List<Barcode>) -> Unit)? = null

    private var delayHandler: Handler? = Handler()
    private var delayCallback = { isActiveScanner = true }
    var delay: Long
    var isActiveDelay: Boolean

    private var detector = BarcodeDetector.Builder(context)
        .setBarcodeFormats(Barcode.ALL_FORMATS)
        .build()

    private lateinit var cameraSource: CameraSource

    private lateinit var shCallback: SurfaceHolderCallback

    var isActiveScanner = true

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScannableCamera)
        delay = typedArray.getInt(R.styleable.ScannableCamera_delayMillis, 1500).toLong()
        isActiveDelay = typedArray.getBoolean(R.styleable.ScannableCamera_isActiveDelay, true)

        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        delayHandler = Handler()
        isActiveScanner = true

        detector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(barcodes: Detector.Detections<Barcode>?) {
                if (!isActiveScanner) return

                val detections = barcodes?.detectedItems ?: return
                if (detections.isEmpty()) return

                if (isActiveDelay) {
                    isActiveScanner = false
                    delayHandler?.postDelayed(delayCallback, delay)
                }

                onScannedCallback?.invoke(detections.toList())
            }
        })

        cameraSource = CameraSource.Builder(context, detector)
            .setAutoFocusEnabled(true)
            .build()

        shCallback = SurfaceHolderCallback(cameraSource)
        holder.addCallback(shCallback)
    }

    fun stopScanning() {
        isActiveScanner = false
        delayHandler = null
    }

    fun resumeScanning() {
        isActiveScanner = true
        delayHandler = Handler()
    }

    fun onScanned(onScanned: ((List<Barcode>) -> Unit)? = null) {
        this.onScannedCallback = onScanned
    }

    override fun onDetachedFromWindow() {
        holder.removeCallback(shCallback)
        delayHandler = null
        cameraSource.stop()
        cameraSource.release()
        super.onDetachedFromWindow()
    }
}

class SurfaceHolderCallback(private val source: CameraSource) : SurfaceHolder.Callback {
    override fun surfaceCreated(holder: SurfaceHolder?) {
        source.start(holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        source.stop()
    }
}