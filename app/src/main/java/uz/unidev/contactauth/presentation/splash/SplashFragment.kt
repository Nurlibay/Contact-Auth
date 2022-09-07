package uz.unidev.contactauth.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.unidev.contactauth.R
import uz.unidev.contactauth.data.local.LocalDataSource

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val navController by lazy { findNavController() }
    private val localStorage = LocalDataSource.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (localStorage.isSigned) {
            lifecycleScope.launch {
                delay(2000)
                navigateMainFragment()
            }
        } else {
            navigateLoginFragment()
        }
    }

    private fun navigateMainFragment() {
        navController.navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }

    private fun navigateLoginFragment() {
        navController.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }
}