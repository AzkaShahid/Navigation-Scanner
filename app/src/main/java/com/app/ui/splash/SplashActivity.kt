package com.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.app.R
import com.app.ui.main.MainViewModel
import com.app.ui.sidenavigation.SideNavigationActivity
import com.app.databinding.ActivitySplashBinding
import com.app.bases.BaseActivity
import com.app.ui.login.LoginActivity
import com.app.ui.main.WelcomeActivity
import com.app.ui.sidenavigation.home.MapActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, MainViewModel>() {

    private val delay: Long = 2000
    override val mViewModel: MainViewModel by viewModels()


    override fun initBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {

        Handler(Looper.getMainLooper()).postDelayed({
            startNextActivity()
        }, delay)
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        mViewBinding.logo.startAnimation(anim)

    }


    override fun addViewModelObservers() {
    }

    override fun attachListens() {

    }

    private fun startNextActivity() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        this.finish()
    }


}