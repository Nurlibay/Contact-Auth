package uz.unidev.contactauth.presentation.auth.register

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
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.databinding.FragmentRegisterBinding
import uz.unidev.contactauth.utils.UiState

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel: RegisterViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            /** register button action */
            btnRegister.setOnClickListener {
                viewModel.register(AuthData(etLogin.text.toString(), etPassword.text.toString()))
                setupObserver()
                lifecycleScope.launch {
                    delay(5000)
                    navigateLoginFragment()
                }
            }
        }
    }

    /** register observe */
    private fun setupObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.register.collect {
                when (it) {
                    is UiState.Loading -> {
                        loading(true)
                    }
                    is UiState.NetworkError -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Register State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Error -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Register State")
                            .setText(it.msg.toString())
                            .show()
                    }
                    is UiState.Success -> {
                        loading(false)
                        Alerter.create(requireActivity())
                            .setTitle("Register State")
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

    private fun navigateLoginFragment() {
        navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}