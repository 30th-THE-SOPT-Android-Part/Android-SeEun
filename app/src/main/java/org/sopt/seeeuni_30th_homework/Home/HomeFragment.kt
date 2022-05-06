package org.sopt.seeeuni_30th_homework.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.seeeuni_30th_homework.R
import org.sopt.seeeuni_30th_homework.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding ?:error("Binding이 초기화되지 않았습니다.")
    private lateinit var HomeTapVpAdapter: HomeTapVpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initAdapter()
        initTabLayout()

        return binding.root
    }

private fun initAdapter() {
    val fragmentList = listOf(GitFollowingFragment(), GitFollowerFragment())

    HomeTapVpAdapter = HomeTapVpAdapter(this)
    HomeTapVpAdapter.fragments.addAll(fragmentList)

    binding.vpHome.adapter = HomeTapVpAdapter
}
    private fun initTabLayout(){
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabHome, binding.vpHome) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}