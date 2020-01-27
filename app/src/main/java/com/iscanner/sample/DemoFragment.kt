package com.iscanner.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_demo.*

class DemoFragment : Fragment(R.layout.fragment_demo) {
    companion object {
        private const val RESULT_ARGUMENT = "result"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        scannerOverlay.cornerColor = Color.GREEN

        scannableCamera.onScanned { barcodes ->
            activity?.runOnUiThread {
                Toast.makeText(requireContext(), barcodes[0].displayValue, Toast.LENGTH_SHORT).show()
            }

            /*activity?.changeFragment(ResultFragment().apply {
                arguments = bundleOf(RESULT_ARGUMENT to barcodes[0].displayValue)
            })*/
        }

        switchOverlay.setOnCheckedChangeListener { buttonView, isChecked ->
            scannerOverlay.isVisible = isChecked
        }

        switchScanning.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) scannableCamera.resumeScanning() else scannableCamera.stopScanning()
        }

        switchMode.setOnCheckedChangeListener { buttonView, isChecked ->
            scannableCamera.isActiveDelay = isChecked
        }
    }


}