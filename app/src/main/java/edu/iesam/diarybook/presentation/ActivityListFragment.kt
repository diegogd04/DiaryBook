package edu.iesam.diarybook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.presentation.adapter.ActivityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val activityAdapter = ActivityAdapter()
    private val viewModel: ActivityListViewModel by viewModel()

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
            adapter = activityAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        viewModel.loadActivities()
    }

    private fun setUpObserver() {
        val observer = Observer<ActivityListViewModel.UiState> { uiState ->
            bindData(uiState.activities)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(activities: List<Activity>) {
        activityAdapter.submitList(activities)
    }
}