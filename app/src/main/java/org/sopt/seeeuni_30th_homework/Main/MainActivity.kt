package org.sopt.seeeuni_30th_homework.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import org.sopt.seeeuni_30th_homework.Camera.CameraFragment
import org.sopt.seeeuni_30th_homework.Home.HomeFragment
import org.sopt.seeeuni_30th_homework.Profile.ProfileFragment
import org.sopt.seeeuni_30th_homework.R
import org.sopt.seeeuni_30th_homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainVpAdapter : MainVpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        mainVpAdapter = MainVpAdapter(this)
        mainVpAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = mainVpAdapter
    }
    private fun initBottomNavi() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position : Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpMain.currentItem = CAMERA_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
    companion object{
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val CAMERA_FRAGMENT = 2
    }
}