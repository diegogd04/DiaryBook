package edu.iesam.diarybook.presentation.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.domain.task.Task
import edu.iesam.diarybook.presentation.task.adapter.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val taskAdapter = TaskAdapter()
    private val viewModel: TaskListViewModel by viewModel()

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
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = taskAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarEdit()
        setUpObserver()
        viewModel.loadTasks()
    }

    private fun setUpObserver() {
        val observer = Observer<TaskListViewModel.UiState> { uiState ->
            bindData(uiState.tasks)
        }

        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(tasks: List<Task>) {
        taskAdapter.submitList(tasks)
    }

    private fun toolbarEdit() {
        binding.toolbar.topAppBar.title = getString(R.string.tasks_title)
    }
}