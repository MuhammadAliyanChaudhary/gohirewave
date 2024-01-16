package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            backArrowForgetPass.setOnClickListener { onButtonClick(it) }
        }
    }


    private fun onButtonClick(it: View?) {
        when (it?.id) {
            R.id.backArrowForgetPass -> {
                onBackPressed()
            }
        }
    }
}