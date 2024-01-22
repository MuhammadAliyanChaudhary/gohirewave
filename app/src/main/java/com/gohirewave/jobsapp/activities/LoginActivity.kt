package com.gohirewave.jobsapp.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()


        with(binding) {
            forgetPasswordTextSignIn.setOnClickListener { onButtonClick(it) }
            loginButtonSignIn.setOnClickListener { onButtonClick(it) }
            googleSignInButton.setOnClickListener { onButtonClick(it) }
            facebookSignInButton.setOnClickListener { onButtonClick(it) }
            signUpLink.setOnClickListener { onButtonClick(it) }
            // Add more click listeners as needed
        }
    }



    private fun onButtonClick(it: View) {
        when (it.id) {
            R.id.forgetPasswordTextSignIn -> {
                openActivity(ForgetPasswordActivity::class.java, null)
            }
            R.id.loginButtonSignIn -> {
                validate()
            }
            R.id.googleSignInButton -> {

            }
            R.id.signUpLink -> {
                openActivity(SignUpActivity::class.java, null)
            }
        }
    }

    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun validate() {

        val email = binding.emailEditTextSignIn.text.toString().trim()
        val pass = binding.passwordEditTextSignIn.text.toString()

        when {
            email.isEmpty() -> {
                binding.emailEditTextSignIn.error = "Email is Empty"
                binding.emailEditTextSignIn.requestFocus()
                showWarningToast(this@LoginActivity,"Email is empty", Toast.LENGTH_LONG)
            }
            pass.isEmpty() -> {
                binding.passwordEditTextSignIn.error = "Password is Empty"
                binding.passwordEditTextSignIn.requestFocus()
                showWarningToast(this@LoginActivity,"Password is empty", Toast.LENGTH_LONG)
            }
            else -> {
                authentication(email, pass)
            }
        }
    }


    private fun authentication(email: String, pass: String) {
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = mAuth.currentUser!!
                    val uid = user.uid


                    val prefsEditor = getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
                    prefsEditor.putString("id", uid)
                    prefsEditor.putBoolean("loggedIn",true)
                    prefsEditor.apply()

                    if (!mAuth.currentUser?.isEmailVerified!!) {
                        openActivity(EmailVerificationActivity::class.java, null)
                    }else{
                        openActivity(BottomNavigationActivity::class.java,null)
                        showSuccessToast(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT)
                        finish()
                    }


                } else {
                    Toast.makeText(this@LoginActivity, task.exception?.message, Toast.LENGTH_LONG).show()
                    showWarningToast(this@LoginActivity,"" + task.exception?.message, Toast.LENGTH_SHORT)
                }
            }
            .addOnFailureListener { e ->
                showWarningToast(this@LoginActivity,"" + e.message, Toast.LENGTH_SHORT)

            }
    }
}