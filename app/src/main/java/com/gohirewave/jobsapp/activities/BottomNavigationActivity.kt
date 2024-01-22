package com.gohirewave.jobsapp.activities

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.databinding.ActivityBottomNavigationBinding
import com.google.firebase.auth.FirebaseAuth

class BottomNavigationActivity : BaseActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_saved_jobs, R.id.navigation_applied_jobs, R.id.navigation_profile
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)





    }

    private fun initializeViews() {
        mAuth = FirebaseAuth.getInstance()
    }
    private fun logoutUser() {
        mAuth.signOut()
        showSuccessToast(this@BottomNavigationActivity, "Logout Successful", Toast.LENGTH_SHORT)
        openActivity(LoginActivity::class.java, null)
        finish()
    }


}

