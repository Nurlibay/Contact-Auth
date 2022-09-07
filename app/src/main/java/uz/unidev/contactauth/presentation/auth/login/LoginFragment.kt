package uz.unidev.contactauth.presentation.auth.login

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
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.databinding.FragmentLoginBinding
import uz.unidev.contactauth.utils.UiState
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login(AuthData(etLogin.text.toString(), etPassword.text.toString()))
                lifecycleScope.launch {
                    delay(5000)
                    navigateMainFragment()
                }
            }
            btnRegister.setOnClickListener {
                navigateRegisterFragment()
            }
        }
    }

    /** login observe */
    private fun setupObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.login.collect {
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

    private fun navigateMainFragment() {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

    private fun navigateRegisterFragment() {
        navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}