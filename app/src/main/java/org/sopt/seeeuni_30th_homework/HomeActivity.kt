package org.sopt.seeeuni_30th_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seeeuni_30th_homework.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: ActivityHomeBinding
    //lateinit : 늦초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {

        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()
        supportFragmentManager.beginTransaction().add(R.id.container_home, followerFragment)
            .commit()

        binding.btnFollow.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            binding.btnFollow.isSelected = true
            binding.btnRepo.isSelected = false
            when (position) {
                REPOSITORY_POSITION -> {
                    transaction.replace(R.id.container_home, followerFragment)
                    position = FOLLOWER_POSITION
                }
            }
            transaction.commit()
        }
        binding.btnRepo.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            binding.btnRepo.isSelected = true
            binding.btnFollow.isSelected = false
            when (position) {
                FOLLOWER_POSITION -> {
                    transaction.replace(R.id.container_home, repositoryFragment)
                    position = REPOSITORY_POSITION
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