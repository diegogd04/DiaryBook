package edu.iesam.diarybook.features.event.presentation.eventDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentEventDetailBinding
import edu.iesam.diarybook.features.event.domain.Event
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EventDetailFragment : Fragment() {

    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventDetailViewModel by viewModel()
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val event = args.eventId
        setUpObserver()
        viewModel.loadEvent(event)
    }

    private fun setUpObserver() {
        val observer = Observer<EventDetailViewModel.UiState> { uiState ->
            if (uiState.event != null) {
                toolbarEdit(uiState.event)
                bindData(uiState.event)
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(event: Event) {
        binding.apply {
            date.text = event.date
            hour.text = event.hour
            duration.text = event.duration
            description.text = event.description
        }
        setOld(event)
    }

    private fun setOld(event: Event) {
        val dateTimeNow = System.currentTimeMillis()
        val eventDateTime = getDateTimeMillis(event.date, event.hour)

        if (dateTimeNow > eventDateTime) {
            viewModel.setEventOld(event, true)
        }
    }

    private fun getDateTimeMillis(date: String, hour: String): Long {
        val eventDateTimeString = "$date $hour"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        val eventDateTime = LocalDateTime.parse(eventDateTimeString, formatter)

        return eventDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    private fun toolbarEdit(event: Event) {
        binding.toolbar.topAppBar.apply {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
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
            title = event.title
        }
    }
}