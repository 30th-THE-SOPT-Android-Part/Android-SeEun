package org.sopt.seeeuni_30th_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.seeeuni_30th_homework.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        initFollowerAdapter()
        return binding.root
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter()
        binding.rvFollowerList.adapter = followerAdapter
        followerAdapter.followerDataList.addAll(
            listOf(
                FollowerData("박세은", "스파크 피엠"),
                FollowerData("박지혜", "스파크 메인피엠"),
                FollowerData("이호재", "스파크 안드"),
                FollowerData("양수빈", "스파크 아요"),
                FollowerData("김영권", "스파크 서버")
            )
        )
    }
}