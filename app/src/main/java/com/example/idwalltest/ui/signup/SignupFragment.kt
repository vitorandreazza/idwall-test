package com.example.idwalltest.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.idwalltest.R
import com.example.idwalltest.databinding.FragmentSignupBinding
import com.example.idwalltest.extensions.requireAppCompatActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.include_toolbar.*

class SignupFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignupBinding.inflate(inflater, container, false)
            .apply {
//                viewModel = homeViewModel
//                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireAppCompatActivity().apply {
            setSupportActionBar(toolbar)
            toolbar.title = getString(R.string.app_name)
        }
    }
}