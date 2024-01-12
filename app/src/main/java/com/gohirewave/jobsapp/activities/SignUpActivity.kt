package com.gohirewave.jobsapp.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private val TAG = "SignUpActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeViews()
        with(binding) {
            signUpButton.setOnClickListener { onButtonClick(it) }
        }
    }

    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun onButtonClick(it: View?) {
        when (it?.id) {
            R.id.signUpButton -> {
                   validate()
            }
        }
    }


    private fun validate() {

        val firstName = binding.firstNameEditTextSignUp.text.toString().trim()
        val lastName = binding.lastNameEditTextSignUp.text.toString().trim()
        val email = binding.emailEditTextSignUp.text.toString().trim()
        val pass = binding.passwordEditTextSignUp.text.toString()
        val confirmPass = binding.passwordAgainEditTextSignUp.text.toString()

        when {
            firstName.isEmpty() -> {
                binding.firstNameEditTextSignUp.error = "Enter First Name"
                binding.firstNameEditTextSignUp.requestFocus()
            }
            lastName.isEmpty() -> {
                binding.lastNameEditTextSignUp.error = "Enter Last Name"
                binding.lastNameEditTextSignUp.requestFocus()
            }
            email.isEmpty() -> {
                binding.emailEditTextSignUp.error = "Enter email"
                binding.emailEditTextSignUp.requestFocus()

            }
            pass.isEmpty() -> {
                binding.passwordEditTextSignUp.error = "Enter password"
                binding.passwordEditTextSignUp.requestFocus()

            }
            confirmPass.isEmpty() -> {
                binding.passwordAgainEditTextSignUp.error = "Enter password again"
                binding.passwordAgainEditTextSignUp.requestFocus()

            }
            pass.length < 6 -> {
                binding.passwordEditTextSignUp.error = "Password length must be greater than 6 digits"
                binding.passwordEditTextSignUp.requestFocus()

            }
            confirmPass.length < 6 -> {
                binding.passwordAgainEditTextSignUp.error = "Password do not match"
                binding.passwordAgainEditTextSignUp.requestFocus()
            }

            pass != confirmPass -> {
                binding.passwordAgainEditTextSignUp.error = "Password do not match"
                binding.passwordAgainEditTextSignUp.requestFocus()

            }
            else -> createUser(email, pass)
        }

    }

    private fun createUser(email: String, pass: String) {
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user?.uid

                    if (uid != null) {
                        dataUpload(email, uid)
                    }

                } else {

                    Toast.makeText(this@SignUpActivity, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->

                Toast.makeText(this@SignUpActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }


    private fun dataUpload(email: String, uid: String) {
        val username = binding.firstNameEditTextSignUp.text.toString()+ " "+
                binding.lastNameEditTextSignUp.text.toString()

        val userMap = hashMapOf(
            "email" to email,
            "username" to username,
            "imageURL" to "default",
            "status" to "offline",
            "id" to uid,
            "bio" to username.toLowerCase()
        )

        val prefsEditor = getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
        prefsEditor.putString("id", uid)
        prefsEditor.apply()

        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(uid).setValue(userMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    sendVerificationLink()
                } else {
                    Toast.makeText(this@SignUpActivity, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun sendVerificationLink(){
        if (!mAuth.currentUser?.isEmailVerified!!) {

            mAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                Log.d(TAG, "onSuccess: email sent")
                Toast.makeText(this@SignUpActivity, "We have sent the link to your registered email address. Please check it.", Toast.LENGTH_SHORT).show()
                openActivity(EmailVerificationActivity::class.java, null)
                finish()
            }?.addOnFailureListener { e ->
                Toast.makeText(this@SignUpActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                openActivity(EmailVerificationActivity::class.java, null)
                finish()
            }
        }
    }



}