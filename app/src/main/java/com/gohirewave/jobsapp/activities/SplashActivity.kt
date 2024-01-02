package com.gohirewave.jobsapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.gohirewave.jobsapp.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayTimer()
    }











    private fun delayTimer(){
       Handler().postDelayed(
            {
                goToLoginActivity()
            }, SPLASH_DISPLAY_LENGTH.toLong()
        )
    }


    private fun goToLoginActivity(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}