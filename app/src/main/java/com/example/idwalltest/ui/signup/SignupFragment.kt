package com.example.idwalltest.ui.signup

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.idwalltest.R
import com.example.idwalltest.databinding.FragmentSignupBinding
import com.example.idwalltest.extensions.appCompatActivity
import com.example.idwalltest.extensions.isDarkTheme
import com.example.idwalltest.extensions.requireAppCompatActivity
import com.example.idwalltest.extensions.shouldShowKeyboard
import com.example.idwalltest.ui.utils.EventObserver
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

class SignupFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SignupViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_signup, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toogle_day_night -> {
                toggleDayNight()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleDayNight() {
        context?.let { context ->
            val theme = if (context.isDarkTheme()) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(theme)
            appCompatActivity?.delegate?.applyDayNight()
        }
    }
}