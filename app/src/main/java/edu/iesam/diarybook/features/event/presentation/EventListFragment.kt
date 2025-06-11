package edu.iesam.diarybook.features.event.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.event.presentation.adapter.EventAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EventListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventListViewModel by viewModel()
    private val eventAdapter = EventAdapter{ event ->
        val action =
            EventListFragmentDirections.actionFromEventListFragmentToDetailEventFragment(event.id)
        findNavController().navigate(action)
    }

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
        binding.apply {
            listItem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = eventAdapter
            }
            buttonActivityAdd.visibility = View.GONE
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
            setEventsOld(uiState.events)
            bindData(uiState.events)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun setEventsOld(events: List<Event>) {
        val dateTimeNow = System.currentTimeMillis()

        events.map { event ->
            val eventDateTime = getDateTimeMillis(event.date, event.hour)
            if (dateTimeNow > eventDateTime) {
                viewModel.setEventOld(event, true)
            }
        }
    }

    private fun getDateTimeMillis(date: String, hour: String): Long {
        val eventDateTimeString = "$date $hour"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val eventDateTime = LocalDateTime.parse(eventDateTimeString, formatter)

        return eventDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
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