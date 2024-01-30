package com.gohirewave.jobsapp.activities.ui.savedjobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.gohirewave.jobsapp.databinding.FragmentSavedJobsBinding

class SavedJobsFragment : Fragment() {

    private lateinit var binding: FragmentSavedJobsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentSavedJobsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}