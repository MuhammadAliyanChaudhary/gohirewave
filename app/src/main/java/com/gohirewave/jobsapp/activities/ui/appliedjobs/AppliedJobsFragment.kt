package com.gohirewave.jobsapp.activities.ui.appliedjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.gohirewave.jobsapp.databinding.FragmentAppliedJobsBinding


class AppliedJobsFragment : Fragment() {

    private lateinit var binding: FragmentAppliedJobsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentAppliedJobsBinding.inflate(inflater, container, false)



        return binding.root
    }


}