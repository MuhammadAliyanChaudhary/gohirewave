package com.gohirewave.jobsapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


   /* val bundle = Bundle().apply {
        putString("media", "Images")
    }

    openActivity(GallaryDataActivity::class.java, bundle)*/

    fun openActivity(calledActivity: Class<*>, bundle: Bundle?) {
        val myIntent = Intent(this, calledActivity)
        if (bundle != null) {
            myIntent.putExtras(bundle)
            if (bundle.getBoolean("clear", false)) {
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
        startActivity(myIntent)
    }


}