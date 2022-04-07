package org.sopt.seeeuni_30th_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.seeeuni_30th_homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}