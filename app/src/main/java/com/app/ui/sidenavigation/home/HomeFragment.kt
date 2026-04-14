package com.app.ui.sidenavigation.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Outline
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.app.R
import com.app.databinding.FragmentHomeBinding
import com.app.bases.BaseFragment
import com.app.broadcast.NetworkCallbackImpl
import com.app.database.CityDBModel
import com.app.databinding.FragmentHome1Binding
import com.app.listeners.AlarmReceiver
import com.app.models.CityModel
import com.app.models.prayer.Data
import com.app.models.prayer.Timings
import com.app.network.Resource
import com.app.ui.adapters.ScannerAndChatBotAdapter
import com.app.utils.NetworkUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.Duration

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHome1Binding, HomeViewModel>()
     {


    override val mViewModel: HomeViewModel by viewModels()


    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHome1Binding {
        return FragmentHome1Binding.inflate(inflater, container, false)
    }

    override fun getToolbarBinding() = null

    override fun getToolbarTitle() = R.string.menu_home

    override fun isMenuButton() = true

    override fun setupUI(savedInstanceState: Bundle?) {
        val pagerAdapter = ScannerAndChatBotAdapter(childFragmentManager,lifecycle)
        mViewBinding.viewPager.adapter = pagerAdapter

        mViewBinding.tabLayout.addTab(mViewBinding.tabLayout.newTab().setText(getString(R.string.scanner)))
        mViewBinding.tabLayout.addTab(mViewBinding.tabLayout.newTab().setText(getString(R.string.chatbot)))

        mViewBinding.viewPager.setPageTransformer { page, position ->
            page.alpha = 0f
            page.animate().alpha(1f).setDuration(400).start()
        }

    }


    override fun attachListener() {
        mViewBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        mViewBinding.viewPager.setCurrentItem(0, true)
                    }
                    1 -> {
                        mViewBinding.viewPager.setCurrentItem(1, true)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Handle tab unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Handle tab reselected
            }
        })
        mViewBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewBinding.tabLayout.getTabAt(position)?.select()
            }
        })


    }


    override fun observeViewModel() {

    }





    override fun onDestroyView() {
        super.onDestroyView()
    }


}
