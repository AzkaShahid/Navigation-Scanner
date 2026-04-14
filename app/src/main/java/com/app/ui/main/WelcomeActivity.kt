package com.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.app.R
import com.app.bases.BaseActivity
import com.app.databinding.ActivityMainBinding
import com.app.databinding.ActivityWelcomeBinding
import com.app.ui.sidenavigation.SideNavigationActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityWelcomeBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        mViewBinding.btnStart.startAnimation(slideUp)

    }

    override fun addViewModelObservers() {
    }

    override fun attachListens() {
        mViewBinding.findLocation.setOnClickListener {
            startActivity(Intent(this, SideNavigationActivity::class.java))

        }

    }
}


