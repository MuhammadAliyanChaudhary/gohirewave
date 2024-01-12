package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityEmailVerificationBinding

class EmailVerificationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEmailVerificationBinding
    private var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        startTimer(60000);  // 60,000 milliseconds = 1 minute
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
}