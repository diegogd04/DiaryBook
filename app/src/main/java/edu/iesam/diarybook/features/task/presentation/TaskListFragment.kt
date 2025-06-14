package edu.iesam.diarybook.features.task.presentation

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
import edu.iesam.diarybook.features.task.domain.Task
import edu.iesam.diarybook.features.task.presentation.adapter.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val taskAdapter = TaskAdapter { task ->
        val action =
            TaskListFragmentDirections.actionFromTaskListFragmentToDetailTaskFragment(task.id)
        findNavController().navigate(action)
    }
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
        binding.apply {
            listItem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = taskAdapter
            }
            buttonActivityAdd.visibility = View.GONE
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
        binding.toolbar.topAppBar.apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.md_theme_tertiary))
            setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.md_theme_onTertiary))
            title = getString(R.string.tasks_title)
        }
    }
}