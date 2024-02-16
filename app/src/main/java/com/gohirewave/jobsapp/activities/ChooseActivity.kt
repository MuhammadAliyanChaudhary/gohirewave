package com.gohirewave.jobsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityChooseBinding

class ChooseActivity : BaseActivity() {
    private lateinit var binding: ActivityChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.postJobBtn.setOnClickListener{
            openActivity(PostJobActivity::class.java, null)
        }

        binding.findJobBtn.setOnClickListener{
            openActivity(BottomNavigationActivity::class.java, null)
        }



    }


}