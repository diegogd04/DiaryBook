package edu.iesam.diarybook.features.login.create.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.diarybook.R
import edu.iesam.diarybook.app.presentation.loadUrl
import edu.iesam.diarybook.databinding.FragmentCreateBinding
import edu.iesam.diarybook.features.login.create.domain.Account
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateViewModel by viewModel()
    private val account = Account(
        "",
        "",
        "",
        Uri.parse("https://static.vecteezy.com/system/resources/previews/011/209/565/non_2x/user-profile-avatar-free-vector.jpg")
    )
    private var img: Uri? = null
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { img ->
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION

            if (img != null) {
                context?.contentResolver?.takePersistableUriPermission(img, flag)
                this.img = img
            }

            binding.photo.loadUrl(img)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        pickPhoto()
        create()
    }

    private fun setUpObserver() {
        val observer = Observer<CreateViewModel.UiState> { uiState ->
            if (uiState.createAccount) {
                findNavController().navigate(R.id.activity_list_fragment)
            }
        }

        return viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun create() {
        binding.apply {
            button.setOnClickListener {
                if (img != null) {
                    account.image = img
                }
                account.name = name.text.toString()
                account.email = email.text.toString()
                account.password = password.text.toString()
                viewModel.createAccount(account)
            }
        }
    }

    private fun pickPhoto() {
        binding.photo.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}