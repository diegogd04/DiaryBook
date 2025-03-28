package edu.iesam.diarybook.features.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.iesam.diarybook.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var auth: FirebaseAuth

    private val email = "diego@gmail.com"
    private val password = "diego2004"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        auth = Firebase.auth
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        binding.apply {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun createUser() {
        auth.createUserWithEmailAndPassword(email, password)
    }

    fun signInUser() {
        auth.signInWithEmailAndPassword(email, password)
    }
}