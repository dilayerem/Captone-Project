package com.example.eshoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.eshoppingapp.R
import com.example.eshoppingapp.databinding.FragmentFirstPageBinding

class FirstPageFragment : Fragment() {

    private lateinit var firstPageBinding: FragmentFirstPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstPageBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_first_page,container,false)
        // Inflate the layout for this fragment
        return firstPageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstPageBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_firstPageFragment_to_signupFragment)
        }
    }
}