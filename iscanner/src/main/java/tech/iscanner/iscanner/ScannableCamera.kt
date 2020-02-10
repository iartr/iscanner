package tech.iscanner.iscanner

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.util.forEach
import androidx.core.util.isEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

/**
 * Main view for scanning QR codes and barcodes
 * Just override it and onScanned callback
 */
class ScannableCamera @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArr: Int = 0
) : SurfaceView(context, attrs, defStyleArr) {

    private lateinit var onScannedCallback: OnScanned

    private var delayHandler: Handler?
    private var delayCallback: (() -> Unit)
    var delay: Long
    var isActiveDelay: Boolean

    private var detector: BarcodeDetector
    private var cameraSource: CameraSource
    private var surfaceHolderCallback: SurfaceHolderCallback

    var isActiveScanner: Boolean

    init {
        delayHandler = Handler()
        delayCallback = { isActiveScanner = true }
        with(context.obtainStyledAttributes(attrs, R.styleable.ScannableCamera)) {
            delay = getInt(R.styleable.ScannableCamera_delayMillis, 1500).toLong()
            isActiveDelay = getBoolean(R.styleable.ScannableCamera_isActiveDelay, true)
            recycle()
        }

        detector = BarcodeDetector.Builder(context)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        cameraSource = CameraSource.Builder(context, detector)
            .setAutoFocusEnabled(true)
            .build()
        surfaceHolderCallback = SurfaceHolderCallback(cameraSource)
        holder.addCallback(surfaceHolderCallback)

        isActiveScanner = true
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
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

                if (::onScannedCallback.isInitialized) {
                    onScannedCallback.onScanned(detections.toList())
                }
            }
        })
    }

    override fun onDetachedFromWindow() {
        holder.removeCallback(surfaceHolderCallback)
        delayHandler = null // for avoid memory leaks
        cameraSource.stop()
        cameraSource.release()
        super.onDetachedFromWindow()
    }

    private class SurfaceHolderCallback(private val source: CameraSource) : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder?) {
            source.start(holder)
        }

        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            source.stop()
        }
    }

    fun stopScanning() {
        isActiveScanner = false
        delayHandler = null
    }

    fun resumeScanning() {
        isActiveScanner = true
        delayHandler = Handler()
    }

    interface OnScanned {
        fun onScanned(barcodes: List<Barcode>)
    }

    fun onScanned(onScannedCallback: OnScanned) {
        this.onScannedCallback = onScannedCallback
    }

    private fun <T> SparseArray<T>.toList(): List<T> {
        return ArrayList<T>().apply {
            this@toList.forEach { _, value -> add(value) }
        }
    }
}