package org.sopt.seeeuni_30th_homework

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.seeeuni_30th_homework.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickEvent()
    }

    private fun isInputComplete(): Boolean {
        val id = binding.idEditText.text
        val pw = binding.pwEditText.text
        val isIdNull = id.isBlank()
        val isPwNull = pw.isBlank()

        return !isIdNull && !isPwNull
    }


    private fun initClickEvent() {
        binding.loginBtn.setOnClickListener {
            if (isInputComplete()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


}