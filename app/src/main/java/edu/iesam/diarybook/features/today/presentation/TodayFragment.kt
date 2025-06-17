package edu.iesam.diarybook.features.today.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentTodayBinding
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.presentation.adapter.ActivityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    private val activityAdapter = ActivityAdapter()
    private val viewModel: TodayViewModel by viewModel()
    private val date = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            listItem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = activityAdapter
            }

            toolbar.apply {
                topAppBar.title = getString(R.string.today_title)
                buttonTaskTodayAdd.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadEventsToday(date)
        setUpObserver()
    }

    private fun setUpObserver() {
        val observer = Observer<TodayViewModel.UiState> { uiState ->
            bindData(uiState.eventsToday)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(eventsToday: List<Event>) {
        activityAdapter.submitList(eventsToday)
    }
}