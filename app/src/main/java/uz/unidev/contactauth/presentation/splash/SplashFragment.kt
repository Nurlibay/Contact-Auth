package uz.unidev.contactauth.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.unidev.contactauth.R
import uz.unidev.contactauth.data.source.local.SharePref

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val navController by lazy { findNavController() }
    private val sharePref = SharePref.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (sharePref.isSigned) {
            lifecycleScope.launch {
                delay(2000)
                navigateContactFragment()
            }
        } else {
            navigateLoginFragment()
        }
    }

    private fun navigateContactFragment() {
        navController.navigate(SplashFragmentDirections.actionSplashFragmentToContactFragment())
    }

    private fun navigateLoginFragment() {
        navController.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }
}