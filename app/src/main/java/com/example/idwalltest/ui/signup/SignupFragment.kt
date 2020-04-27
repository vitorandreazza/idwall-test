package com.example.idwalltest.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.idwalltest.R
import com.example.idwalltest.databinding.FragmentSignupBinding
import com.example.idwalltest.extensions.requireAppCompatActivity
import com.example.idwalltest.extensions.shouldShowKeyboard
import com.example.idwalltest.ui.utils.EventObserver
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_signup.*
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
        toolbar.setTitle(R.string.app_name)
        requireAppCompatActivity().setSupportActionBar(toolbar)

        viewModel.navigateToFeedEvent.observe(viewLifecycleOwner, EventObserver {
            context?.shouldShowKeyboard(view, show = false)
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToFeedFragment())
        })
    }
}