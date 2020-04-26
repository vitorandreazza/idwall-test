package com.example.idwalltest.ui.feed

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.idwalltest.R
import com.example.idwalltest.databinding.FragmentFeedBinding
import com.example.idwalltest.extensions.requireAppCompatActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.include_toolbar.view.*
import javax.inject.Inject

class FeedFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<FeedViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.setTitle(R.string.common_feed)
        requireAppCompatActivity().setSupportActionBar(toolbar)
        val feedAdapter = FeedAdapter(viewModel)
        recyclerview_feed.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = feedAdapter
        }
        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
        frame_scrim.setOnClickListener {
            it.isVisible = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_feed, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                val view = requireActivity().findViewById<View>(item.itemId)
                inflateFilterPopMenu(view)
            }
        }
        return true
    }

    private fun inflateFilterPopMenu(anchorView: View) {
        val popupMenu = PopupMenu(requireContext(), anchorView).apply {
            menuInflater.inflate(R.menu.popmenu_feed_filters, menu)
            setOnMenuItemClickListener {
                viewModel.setFilter(it.itemId)
                true
            }
        }
        popupMenu.show()
    }
}