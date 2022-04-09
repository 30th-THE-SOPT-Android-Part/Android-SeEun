package org.sopt.seeeuni_30th_homework

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.seeeuni_30th_homework.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupFinishBtn.setOnClickListener {

            val name = binding.nameEditText.text.toString()
            val id = binding.idEditText.text.toString()
            val pw = binding.pwEditText.text.toString()

            if (isInputComplete(name, id, pw)) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                if(!isFinishing) finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isInputComplete(name: String, id: String, pw: String): Boolean {
        val isNameNull = name.isBlank()
        val isIdNull = id.isBlank()
        val isPwNull = pw.isBlank()

        // true  && true = true
        // true && false = flase
        // false && false = false
        // false && false = flase
        // true || true = true
        // ture || false = true
        // false|| false = false

        return !isNameNull && !isIdNull && !isPwNull
    }
}