package edu.iesam.diarybook.features.task.presentation.taskDetail

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
import edu.iesam.diarybook.databinding.FragmentTaskDetailBinding
import edu.iesam.diarybook.features.task.domain.Task
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailFragment : Fragment() {

    private var _binding: FragmentTaskDetailBinding? = null
    val binding get() = _binding!!
    private val viewModel: TaskDetailViewModel by viewModel()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val task = args.taskId
        setUpObserver()
        viewModel.loadTask(task)
    }

    private fun setUpObserver() {
        val observer = Observer<TaskDetailViewModel.UiState> { uiState ->
            if (uiState.task != null) {
                toolbarEdit(uiState.task)
                bindData(uiState.task)
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(task: Task) {
        binding.apply {
            description.text = task.description
        }
    }

    private fun toolbarEdit(task: Task) {
        binding.toolbar.topAppBar.apply {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.md_theme_tertiary
                )
            )
            setTitleTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.md_theme_onTertiary
                )
            )
            title = task.title
        }
    }
}