package edu.iesam.diarybook.features.event.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.event.presentation.adapter.EventAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventListViewModel by viewModel()
    private val eventAdapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityListBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.listItem.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = eventAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarEdit()
        setUpObserver()
        viewModel.loadEvents()
    }

    private fun setUpObserver() {
        val observer = Observer<EventListViewModel.UiState> { uiState ->
            bindData(uiState.events)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(events: List<Event>) {
        eventAdapter.submitList(events)
    }

    private fun toolbarEdit() {
        binding.toolbar.topAppBar.apply {
            setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.md_theme_secondaryFixedDim_highContrast
                )
            )
            setTitleTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.md_theme_onSecondaryFixedVariant_highContrast
                )
            )
            title = getString(R.string.events_title)
        }
    }
}