package com.gohirewave.jobsapp.activities.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.activities.LoginActivity
import com.gohirewave.jobsapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()

        binding.profileToolbar.titleTextToolbarMain.text = getString(R.string.title_profile)

        binding.logoutBtn.setOnClickListener{
            logoutUser()
        }

        return binding.root
    }

    private fun logoutUser() {
        mAuth.signOut()
        FancyToast.makeText(requireContext(), "Logout Successful", FancyToast.LENGTH_SHORT, R.drawable.ic_logo, false )

        val myIntent = Intent(requireContext(), LoginActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(myIntent)

    }

}