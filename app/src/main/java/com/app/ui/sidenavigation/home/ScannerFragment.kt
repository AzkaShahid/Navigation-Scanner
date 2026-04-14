package com.app.ui.sidenavigation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.R
import com.app.bases.BaseFragment
import com.app.databinding.FragmentScannerBinding
import com.app.scanner.MapActivity1
import com.app.scanner.ScannerActivity
import com.google.zxing.integration.android.IntentIntegrator

class ScannerFragment : BaseFragment<FragmentScannerBinding, HomeViewModel>() {

    override val mViewModel: HomeViewModel by viewModels()

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentScannerBinding {
        return FragmentScannerBinding.inflate(inflater, container, false)
    }

    override fun getToolbarBinding() = null

    override fun getToolbarTitle() = R.string.menu_home

    override fun isMenuButton() = true

    override fun setupUI(savedInstanceState: Bundle?) {}

    override fun attachListener() {
        mViewBinding.btnScanner.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setPrompt("Scan the QR code")
            integrator.setBeepEnabled(true)
            integrator.setOrientationLocked(false)
            integrator.captureActivity = ScannerActivity::class.java
            integrator.initiateScan()
        }
    }

    override fun observeViewModel() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            try {
                val parts = result.contents.split(",")
                val lat = parts[0].substringAfter("lat:").toDouble()
                val lng = parts[1].substringAfter("lng:").toDouble()
                val name = parts[2].substringAfter("name:")

                val intent = Intent(requireContext(), MapActivity::class.java)
                intent.putExtra("lat", lat)
                intent.putExtra("lng", lng)
                intent.putExtra("name", name)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(context, "Invalid QR code format", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}