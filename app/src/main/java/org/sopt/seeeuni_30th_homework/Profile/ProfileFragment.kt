package org.sopt.seeeuni_30th_homework.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.sopt.seeeuni_30th_homework.Follower.FollowerFragment
import org.sopt.seeeuni_30th_homework.Home.HomeActivity
import org.sopt.seeeuni_30th_homework.R
import org.sopt.seeeuni_30th_homework.Repository.RepositoryFragment
import org.sopt.seeeuni_30th_homework.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initTransactionEvent()

        Glide.with(this)
            .load(R.drawable.seeun)
            .circleCrop()
            .into(binding.imgSeeun)
        return binding.root

    }

    private fun initTransactionEvent() {

        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()
            childFragmentManager.beginTransaction().add(R.id.container_home, followerFragment)
            .commit()

        binding.btnFollow.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            binding.btnFollow.isSelected = true
            binding.btnRepo.isSelected = false
            when (position) {
                HomeActivity.REPOSITORY_POSITION -> {
                    transaction.replace(R.id.container_home, followerFragment)
                    position = HomeActivity.FOLLOWER_POSITION
                }
            }
            transaction.commit()
        }
        binding.btnRepo.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            binding.btnRepo.isSelected = true
            binding.btnFollow.isSelected = false
            when (position) {
                HomeActivity.FOLLOWER_POSITION -> {
                    transaction.replace(R.id.container_home, repositoryFragment)
                    position = HomeActivity.REPOSITORY_POSITION
                }
            }
            transaction.commit()
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPOSITORY_POSITION = 2
    }
}