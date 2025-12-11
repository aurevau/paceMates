package com.example.pacematesapplication

import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity)  {
    val calenderFragment = CalenderFragment()
    val chatFragment = ChatFragment()
    val profileFragment = ProfileFragment()
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> chatFragment
            1 -> profileFragment
            2 -> calenderFragment
            else -> chatFragment
        }
    }

    override fun getItemCount(): Int = 2


}