package edu.iesam.diarybook.features.today.todayTaskAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentTodayTaskAddBinding
import edu.iesam.diarybook.features.task.domain.Task
import edu.iesam.diarybook.features.today.todayTaskAdd.adapter.TodayTaskAddAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodayTaskAddFragment : Fragment() {

    private var _binding: FragmentTodayTaskAddBinding? = null
    private val binding get() = _binding!!
    private val todayTaskAddAdapter = TodayTaskAddAdapter { task ->
        viewModel.addTaskToday(task.id, true)
    }
    private val viewModel: TodayTaskAddViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayTaskAddBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            toolbar.apply {
                topAppBar.title = getString(R.string.today_task_add_title)
            }
            listItem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = todayTaskAddAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTasksNotCompleted()
        setUpObserver()
    }

    private fun setUpObserver() {
        val observer = Observer<TodayTaskAddViewModel.UiState> { uiState ->
            bindData(uiState.tasksNotCompleted)
            if (uiState.addTaskTodaySuccess) {
                findNavController().navigateUp()
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(tasks: List<Task>) {
        todayTaskAddAdapter.submitList(tasks)
    }
}