package com.example.pacematesapplication

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.pacematesapplication.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var tabBar: BottomNavigationView
    private lateinit var viewPager: ViewPager2
    lateinit var searchButton: ImageButton

    val chatFragment = ChatFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabBar = binding.bottomNavigation
        viewPager = binding.vpPager
        searchButton = binding.btnSearch

        searchButton.setOnClickListener {
            val intent = Intent(this, SearchUserActivity::class.java)
            startActivity(intent)
        }
        val adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.itemCount

        tabBar.setOnItemSelectedListener{ item ->
         when(item.itemId){
             R.id.menu_chat -> {
                 viewPager.currentItem = 0
                 true
                 }
             R.id.menu_calender -> {
                 viewPager.currentItem = 1
                 true
             }
             R.id.menu_profile -> {
                 viewPager.currentItem = 2
                 true
             }
             else -> false
         }
        }

    }


}