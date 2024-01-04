package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            emailEditTextSignIn.setOnClickListener { onButtonClick(it) }
            passwordEditTextSignIn.setOnClickListener { onButtonClick(it) }
            forgetPasswordTextSignIn.setOnClickListener { onButtonClick(it) }
            loginButtonSignIn.setOnClickListener { onButtonClick(it) }
            googleSignInButton.setOnClickListener { onButtonClick(it) }
            facebookSignInButton.setOnClickListener { onButtonClick(it) }
            signInLink.setOnClickListener { onButtonClick(it) }
            // Add more click listeners as needed
        }
    }



    private fun onButtonClick(it: View) {
        when (it.id) {
            R.id.emailEditTextSignIn -> {

            }
            R.id.passwordEditTextSignIn -> {

            }
            R.id.forgetPasswordTextSignIn -> {

            }
            R.id.loginButtonSignIn -> {

            }
            R.id.googleSignInButton -> {

            }
            R.id.signInLink -> {

            }
        }
    }
}