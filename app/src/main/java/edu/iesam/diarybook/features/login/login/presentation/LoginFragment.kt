package edu.iesam.diarybook.features.login.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentLoginBinding
import edu.iesam.diarybook.features.login.login.domain.Account
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()
    private val account = Account("", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageAccount()
        setUpObserver()
    }

    private fun setUpObserver() {
        val observer = Observer<LoginViewModel.UiState> { uiState ->
            if (uiState.signInAccount) {
                findNavController().navigate(R.id.activity_list_fragment)
            }
        }

        return viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun manageAccount() {
        signIn()
        goToCreateView()
    }

    private fun signIn() {
        binding.apply {
            signInButton.setOnClickListener {
                account.email = email.text.toString()
                account.password = password.text.toString()
                viewModel.signInAccount(account)
            }
        }
    }

    private fun goToCreateView() {
        binding.apply {
            loginCreateButton.setOnClickListener {
                findNavController().navigate(R.id.create_fragment)
            }
        }
    }
}