package com.gohirewave.jobsapp.activities.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gohirewave.jobsapp.R
import com.gohirewave.jobsapp.activities.LoginActivity
import com.gohirewave.jobsapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)


        mAuth = FirebaseAuth.getInstance()





        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun logoutUser() {
        mAuth.signOut()
        FancyToast.makeText(requireContext(), "Logout Successful", FancyToast.LENGTH_SHORT, R.drawable.ic_logo, false )

        val myIntent = Intent(requireContext(), LoginActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(myIntent)

    }
}