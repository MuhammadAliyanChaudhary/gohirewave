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

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.includeToolbar.titleTextToolbarMain.text = getString(R.string.title_home)


        mAuth = FirebaseAuth.getInstance()






        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


}