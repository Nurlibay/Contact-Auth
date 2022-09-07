package uz.unidev.contactauth.presentation.main.add

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import uz.unidev.contactauth.R
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.databinding.FragmentAddContactBinding
import uz.unidev.contactauth.utils.UiState

@AndroidEntryPoint
class AddContactFragment : Fragment(R.layout.fragment_add_contact) {

    private val binding: FragmentAddContactBinding by viewBinding()
    private val viewModel: AddContactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            /** back button click action */
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            /** add button click action */
            btnAdd.setOnClickListener {
                viewModel.addContact(ContactRequest(etLogin.text.toString(), etPassword.text.toString()))
                setupObserver()
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.addContact.collect {
                when (it) {
                    is UiState.Loading -> {
                        loading(true)
                    }
                    is UiState.NetworkError -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Error -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Success -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("State")
                            .setText(it.data)
                            .show()
                    }
                    else -> {
                        loading(false)
                    }
                }
            }
        }
    }

    private fun loading(state: Boolean) {
        binding.progress.isVisible = state
    }
}