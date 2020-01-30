package tech.iscanner.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.vision.barcode.Barcode
import tech.iscanner.iscanner.hasBarcodeType
import tech.iscanner.iscanner.isQr
import kotlinx.android.synthetic.main.fragment_demo.*

class DemoFragment : Fragment(R.layout.fragment_demo) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        scannableCamera.onScanned { barcodes ->
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

        switchOverlay.setOnCheckedChangeListener { _, isChecked ->
            scannerOverlay.isVisible = isChecked
        }

        switchScanning.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) scannableCamera.resumeScanning() else scannableCamera.stopScanning()
        }

        switchMode.setOnCheckedChangeListener { _, isChecked ->
            scannableCamera.isActiveDelay = isChecked
        }
    }


}