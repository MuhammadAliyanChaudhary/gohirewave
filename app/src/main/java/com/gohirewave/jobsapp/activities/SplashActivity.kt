package com.gohirewave.jobsapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.gohirewave.jobsapp.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : BaseActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initializeViews()



        delayTimer()
    }







    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }



    private fun delayTimer(){
       Handler().postDelayed(
            {
                sendUserToActivities()
            }, SPLASH_DISPLAY_LENGTH.toLong()
        )
    }

    private fun sendUserToActivities() {
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            if(mAuth.currentUser?.isEmailVerified!!){
                openActivity(BottomNavigationActivity::class.java, null)
                finish()
            }else{
                openActivity(EmailVerificationActivity::class.java, null)
                finish()
            }

        } else {
            openActivity(LoginActivity::class.java, null)
            finish()
        }
    }


}