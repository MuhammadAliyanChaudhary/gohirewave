package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityMainBinding
import com.shashank.sony.fancytoastlib.FancyToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.showToast.setOnClickListener {
            FancyToast.makeText(this, "Successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,R.drawable.ic_logo, false).show()
        }

    }
}