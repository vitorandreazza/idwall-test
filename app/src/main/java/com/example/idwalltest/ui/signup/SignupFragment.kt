package com.example.idwalltest.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.idwalltest.R
import com.example.idwalltest.databinding.FragmentSignupBinding
import com.example.idwalltest.extensions.requireAppCompatActivity
import com.example.idwalltest.ui.utils.EventObserver
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class SignupFragment : DaggerFragment() {

    @Inject lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SignupViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignupBinding.inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireAppCompatActivity().apply {
            setSupportActionBar(toolbar)
            toolbar.title = getString(R.string.app_name)
        }
        viewModel.navigateToFeedEvent.observe(viewLifecycleOwner, EventObserver {
            Log.d("TEST", "NAVIGATE")
        })
    }
}