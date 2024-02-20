package com.gohirewave.jobsapp.activities.company

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.activities.company.fragments.CompanyProfileEditFragment
import com.gohirewave.jobsapp.activities.company.fragments.JobsPostedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CompanyBottomNavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment?
        when (item.itemId) {
            R.id.navigation_jobs_posted -> {
                selectedFragment = JobsPostedFragment()
                true
            }
            R.id.navigation_company_profile -> {
                selectedFragment = CompanyProfileEditFragment()
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_bottom_navigation)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Set the default fragment to display
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            JobsPostedFragment()).commit()
    }
}
