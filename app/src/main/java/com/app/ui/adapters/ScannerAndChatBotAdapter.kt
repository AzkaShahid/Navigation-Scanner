package com.app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.ui.sidenavigation.home.ChatBotFragment
import com.app.ui.sidenavigation.home.ScannerFragment

class ScannerAndChatBotAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScannerFragment()
            1 -> ChatBotFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}