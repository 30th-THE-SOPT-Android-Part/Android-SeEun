package org.sopt.seeeuni_30th_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.seeeuni_30th_homework.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var repositoryAdapter: RepositoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        initRepositoryAdapter()
        return binding.root
    }
private fun initRepositoryAdapter() {
    repositoryAdapter = RepositoryAdapter()
    binding.rvRepositoryList.adapter = repositoryAdapter
    repositoryAdapter.repositoryDataList.addAll(
        listOf(
            RepositoryData("안드 과제 레포지토리", "안드로이드 파트 과제"),
            RepositoryData("iOS 과제 레포지토리", "iOS 파트 과제제제제제제제제제제제제제ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ"),
            RepositoryData("서버 과제 레포지토리", "서버 파트 과제"),
            RepositoryData("기획 과제 레포지토리", "기획 파트 과제"),
        )
    )
}
}