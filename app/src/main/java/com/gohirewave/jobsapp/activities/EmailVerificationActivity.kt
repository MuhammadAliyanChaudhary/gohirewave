package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityEmailVerificationBinding
import com.google.firebase.auth.FirebaseAuth

class EmailVerificationActivity : BaseActivity() {

    private lateinit var binding : ActivityEmailVerificationBinding
    private var countDownTimer: CountDownTimer? = null
    private lateinit var mAuth: FirebaseAuth
    private val TAG = "EmailVerificationActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeViews()
        sendVerificationLink()
        with(binding) {
            backArrowEmailVerify.setOnClickListener { onButtonClick(it) }
            verifyBtn.setOnClickListener { onButtonClick(it) }
            sendVerificationLink.setOnClickListener { onButtonClick(it) }
            logoutBtnEmailVerification.setOnClickListener { onButtonClick(it) }
        }





        startTimer(60000);  // 60,000 milliseconds = 1 minute





    }



    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }



    private fun onButtonClick(it: View?) {
        when (it?.id) {
            R.id.backArrowEmailVerify -> {
                onBackPressed()
            }
            R.id.verify_btn ->{
                checkVerification()
            }
            R.id.send_verification_link ->{
                sendVerificationLink()
            }
            R.id.logout_btn_email_verification -> {
                logoutUser()
            }
        }
    }

    private fun logoutUser() {
        mAuth.signOut()
        showSuccessToast(this@EmailVerificationActivity, "Logout Successful", Toast.LENGTH_SHORT)
        openActivity(LoginActivity::class.java, null)
        finish()
    }

    private fun startTimer(durationMillis: Long) {
        countDownTimer = object : CountDownTimer(durationMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the UI with the remaining time
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                // Perform actions when the timer finishes (e.g., hide the timer, show a message)
                binding.timerTextView.text = "Time's up!"
            }
        }

        countDownTimer?.start()
    }

    private fun updateTimerText(millisUntilFinished: Long) {
        // Format the remaining time and update the TextView
        val secondsRemaining = millisUntilFinished / 1000
        binding.timerTextView.text = "Wait until: $secondsRemaining seconds"
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }


    private fun sendVerificationLink(){
        if (!mAuth.currentUser?.isEmailVerified!!) {

            mAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                Log.d(TAG, "onSuccess: email sent")
                showSuccessToast(this@EmailVerificationActivity, "We have sent the link to your registered email address. Please check it.", Toast.LENGTH_LONG)
            }?.addOnFailureListener { e ->
                showWarningToast(this@EmailVerificationActivity, "${e.message}", Toast.LENGTH_SHORT)
            }
        }else{
            showSuccessToast(this@EmailVerificationActivity, "Verification Successful", Toast.LENGTH_SHORT)
            openActivity(BottomNavigationActivity::class.java, null)
        }
    }

    private fun checkVerification(){
        if(mAuth.currentUser?.isEmailVerified!!){
            showSuccessToast(this@EmailVerificationActivity, "Verification Successful", Toast.LENGTH_SHORT)
            openActivity(BottomNavigationActivity::class.java, null)
        }
        if(!mAuth.currentUser?.isEmailVerified!!){
            showWarningToast(this@EmailVerificationActivity, "Email not verified", Toast.LENGTH_SHORT)
        }
    }

}