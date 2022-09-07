package uz.unidev.contactauth.presentation.main.update

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import uz.unidev.contactauth.R
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.databinding.FragmentUpdateContactBinding
import uz.unidev.contactauth.utils.UiState

@AndroidEntryPoint
class UpdateContactFragment : Fragment(R.layout.fragment_update_contact) {

    private val binding: FragmentUpdateContactBinding by viewBinding()
    private val navArgs: UpdateContactFragmentArgs by navArgs()
    private val viewModel: UpdateContactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setupUI()
            /** back button click action */
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            /** update button click action */
            btnUpdate.setOnClickListener {
                viewModel.updateContact(navArgs.contact.id, ContactRequest(etLogin.text.toString(), etPassword.text.toString()))
                setupObserver()
            }
        }
    }

    private fun FragmentUpdateContactBinding.setupUI() {
        etLogin.setText(navArgs.contact.name)
        etPassword.setText(navArgs.contact.phone)
    }

    /** update observe */
    private fun setupObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.update.collect {
                when (it) {
                    is UiState.Loading -> {
                        loading(true)
                    }
                    is UiState.NetworkError -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Update Contact State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Error -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Update Contact State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Success -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Update Contact State")
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