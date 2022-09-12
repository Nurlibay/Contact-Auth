package uz.unidev.contactauth.presentation.main.contact

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.unidev.contactauth.R
import uz.unidev.contactauth.data.source.local.SharePref
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.databinding.FragmentContactBinding
import uz.unidev.contactauth.utils.UiState

@AndroidEntryPoint
class ContactFragment : Fragment(R.layout.fragment_contact) {

    private val navController by lazy { findNavController() }
    private val adapter = ContactAdapter()
    private val binding: FragmentContactBinding by viewBinding()
    private val viewModel: ContactViewModel by viewModels()

    private val sharePref = SharePref.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signedAccount()
        setupObserverAllContacts()
        binding.apply {
            rvContacts.adapter = adapter

            /** fab button click event */
            fab.setOnClickListener {
                navigateAddContactFragment()
            }

            /** logout event */
            ivLogout.setOnClickListener {
                viewModel.logout(AuthData(sharePref.getName()!!, sharePref.getPassword()!!))
                setupObserverLogoutEvent()
                sharePref.saveToken("")
                lifecycleScope.launch {
                    delay(2000)
                    navigateLoginFragment()
                }
            }

            /** unregister event */
            ivUnregister.setOnClickListener {
                viewModel.unregister(AuthData(sharePref.getName()!!, sharePref.getPassword()!!))
                setupObserverUnregisterEvent()
                sharePref.saveToken("")
                lifecycleScope.launch {
                    delay(2000)
                    navigateLoginFragment()
                }
            }

            /** update icon click event */
            adapter.setOnUpdateIconClickListener {
                navigateUpdateFragment(it)
            }

            /** delete icon event */
            adapter.setOnDeleteIconClickListener { contact ->
                viewModel.deleteContact(contact)
                setupObserverDeleteContactEvent()
                lifecycleScope.launch {
                    delay(1000)
                    viewModel.getAllContacts()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllContacts()
    }

    private fun signedAccount() {
        sharePref.isSigned = true
    }

    private fun setupObserverLogoutEvent() {
        lifecycleScope.launchWhenResumed {
            viewModel.logout.collect {
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
                            .setText(it.toString())
                            .show()
                    }
                    else -> {
                        loading(false)
                    }
                }
            }
        }
    }

    private fun setupObserverDeleteContactEvent() {
        lifecycleScope.launch {
            viewModel.deleteContact.collect {
                when (it) {
                    is UiState.Loading -> {
                        loading(true)
                    }
                    is UiState.NetworkError -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Delete State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Error -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Delete State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Success -> {
                        loading(false)
                    }
                    else -> {
                        loading(false)
                    }
                }
            }
        }
    }

    private fun setupObserverAllContacts() {
        lifecycleScope.launchWhenResumed {
            viewModel.contacts.collect {
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
                        adapter.submitList(it.data)
                    }
                    else -> {
                        loading(false)
                    }
                }
            }
        }
    }

    private fun setupObserverUnregisterEvent() {
        lifecycleScope.launchWhenResumed {
            viewModel.unregister.collect {
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
                        Alerter.create(requireActivity())
                            .setTitle("State")
                            .setText(it.data)
                            .show()
                        loading(false)
                    }
                    else -> {
                        loading(false)
                    }
                }
            }
        }
    }

    private fun loading(b: Boolean) {
        binding.progressBar.isVisible = b
    }

    private fun navigateLoginFragment() {
        navController.navigate(ContactFragmentDirections.actionContactFragmentToLoginFragment())
    }

    private fun navigateAddContactFragment() {
        navController.navigate(ContactFragmentDirections.actionContactFragmentToAddContactFragment())
    }

    private fun navigateUpdateFragment(it: ContactResponse) {
        navController.navigate(ContactFragmentDirections.actionContactFragmentToUpdateContactFragment(it))
    }
}