package edu.iesam.diarybook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentActivityListBinding
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.presentation.adapter.ActivityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityListFragment : Fragment() {

    private var _binding: FragmentActivityListBinding? = null
    private val binding get() = _binding!!
    private val activityAdapter = ActivityAdapter()

    /*private val activityAdapter = ActivityAdapter{ activity ->
        val action = if (activity is Event){
            ActivityListFragmentDirections.actionFromActivityListFragmentToDetailActivityFragment(activity, null)
        }else if (activity is Task){
            ActivityListFragmentDirections.actionFromActivityListFragmentToDetailActivityFragment(null, activity)
        } else {
            return@ActivityAdapter
        }

        findNavController().navigate(action)
    }*/
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
        binding.apply {
            listItem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = activityAdapter
            }
            buttonActivityAdd.setOnClickListener {
                findNavController().navigate(R.id.action_from_activity_list_fragment_to_create_activity_fragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser?.uid

        setUpObserver()
        if (currentUser != null) {
            viewModel.loadActivities()
        }
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