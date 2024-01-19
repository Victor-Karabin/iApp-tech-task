package com.iapps.presentation.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiapps.R
import com.iapps.core.lazyNone
import com.iapps.presentation.openLinkInBrowser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatsFragment : Fragment() {

    private val viewModel by viewModels<CatsViewModel>()
    private val catsAdapter by lazyNone {
        CatsAdapter { item -> this.context?.openLinkInBrowser(item.link) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanCount = if (resources.getBoolean(R.bool.isTablet)) 3 else 1

        val recyclerView = view.findViewById<RecyclerView>(R.id.rcv_cats)
        recyclerView.adapter = catsAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.catItems.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { items -> catsAdapter.setItems(items) }
        }

        val progress = view.findViewById<ProgressBar>(R.id.progress)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.progress.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { visible ->
                    progress.visibility = if (visible) View.VISIBLE else View.GONE
                }
        }

        val error = view.findViewById<TextView>(R.id.tv_error)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { text -> error.text = text }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadItems()
    }
}