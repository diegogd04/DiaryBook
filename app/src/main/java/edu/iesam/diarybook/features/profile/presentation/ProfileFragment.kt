package edu.iesam.diarybook.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.diarybook.R
import edu.iesam.diarybook.app.presentation.loadUrl
import edu.iesam.diarybook.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            completedActivities.title.text = getString(R.string.profile_completed_activities_title)
            userDelete.buttonText.text = getString(R.string.profile_action_user_delete)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadUser()
        setUpObserver()
        managerAccount()
    }

    private fun setUpObserver() {
        val observer = Observer<ProfileViewModel.UiState> { uiState ->
            if (uiState.deleteAccount || uiState.signOutAccount) {
                findNavController().navigate(R.id.login_fragment)
            }
            binding.apply {
                photo.loadUrl(uiState.user?.photo)
                title.text = getString(R.string.profile_head_title, uiState.user?.name)
                email.text = uiState.user?.email
            }
        }
        return viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun managerAccount() {
        signOutAccount()
        deleteAccount()
    }

    private fun signOutAccount() {
        binding.apply {
            userLogout.button.setOnClickListener {
                viewModel.signOutAccount()
            }
        }
    }

    private fun deleteAccount() {
        binding.apply {
            userDelete.button.setOnClickListener {
                deleteBackground.root.visibility = View.VISIBLE
                dialogConfirmDelete.root.visibility = View.VISIBLE
            }
            dialogConfirmDelete.accept.setOnClickListener {
                val email = dialogConfirmDelete.emailInput.text.toString()
                val password = dialogConfirmDelete.passwordInput.text.toString()
                viewModel.deleteAccount(email, password)
            }
            dialogConfirmDelete.cancel.setOnClickListener {
                deleteBackground.root.visibility = View.GONE
                dialogConfirmDelete.root.visibility = View.GONE
            }
        }
    }
}