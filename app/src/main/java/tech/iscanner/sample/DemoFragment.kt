package tech.iscanner.sample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.vision.barcode.Barcode
import tech.iscanner.iscanner.hasBarcodeType
import tech.iscanner.iscanner.isQr
import kotlinx.android.synthetic.main.fragment_demo.*
import tech.iscanner.iscanner.ScannableCamera

class DemoFragment : Fragment(R.layout.fragment_demo) {
    private companion object {
        private const val CAMERA_REQUEST = 1024
    }

    private var isFlash = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
        } else {
            // IT IS HANDLING IN LIBRARY!
        }

        scannableCamera.onScanned(object : ScannableCamera.OnScanned {
            override fun onScanned(barcodes: List<Barcode>) {
                if (barcodes.hasBarcodeType(Barcode.QR_CODE)) {
                    barcodes.forEach { barcode -> // Toast all QRs
                        if (barcode.isQr()) {
                            activity?.runOnUiThread {
                                Toast.makeText(requireContext(), barcode.displayValue, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        })

        switchOverlay.setOnCheckedChangeListener { _, isChecked ->
            scannerOverlay.isVisible = isChecked
        }

        switchScanning.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) scannableCamera.resumeScanning() else scannableCamera.stopScanning()
        }

        switchMode.setOnCheckedChangeListener { _, isChecked ->
            scannableCamera.isActiveDelay = isChecked
        }

        flashButton.setOnClickListener {
            isFlash = !isFlash
            scannableCamera.flash(isFlash)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scannableCamera.cameraSource.start(scannableCamera.holder)
            } else {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
            }
        }
    }
}