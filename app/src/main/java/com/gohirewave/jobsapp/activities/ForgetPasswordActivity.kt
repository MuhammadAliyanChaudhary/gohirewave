package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.BaseSavedState
import android.widget.Toast
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ForgetPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()

        with(binding) {
            backArrowForgetPass.setOnClickListener { onButtonClick(it) }
            btnResetPassGetLink.setOnClickListener { onButtonClick(it) }
        }
    }

    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun onButtonClick(it: View?) {
        when (it?.id) {
            R.id.backArrowForgetPass -> {
                onBackPressed()
            }
            R.id.btnResetPassGetLink ->{
                    sendLink(binding.emailEditTextForget.toString().trim())
            }
        }
    }


    private fun sendLink(email: String) {
        mAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showSuccessToast(this@ForgetPasswordActivity,"Reset Password link sent to your email",
                        Toast.LENGTH_LONG )
                        onBackPressed()
                } else {
                    showWarningToast(this@ForgetPasswordActivity,"${task.exception?.message}",
                        Toast.LENGTH_SHORT )
                }
            }
            .addOnFailureListener { e ->
                showWarningToast(this@ForgetPasswordActivity, "${e.message}", Toast.LENGTH_SHORT)
            }
    }
}