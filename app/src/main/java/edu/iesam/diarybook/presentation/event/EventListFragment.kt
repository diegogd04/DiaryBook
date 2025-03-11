package edu.iesam.diarybook.presentation.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.domain.event.Event
import edu.iesam.diarybook.presentation.event.adapter.EventAdapter
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
}