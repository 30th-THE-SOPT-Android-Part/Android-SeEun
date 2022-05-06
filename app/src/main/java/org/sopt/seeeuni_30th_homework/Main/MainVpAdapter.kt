package org.sopt.seeeuni_30th_homework.Main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainVpAdapter (fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
            val fragments = mutableListOf<Fragment>()
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
    }
