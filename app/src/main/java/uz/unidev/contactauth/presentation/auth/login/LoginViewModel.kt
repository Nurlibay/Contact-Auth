package uz.unidev.contactauth.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.domain.usecases.auth.LoginUseCase
import uz.unidev.contactauth.utils.UiState
import uz.unidev.contactauth.utils.hasConnection
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 05/09/2022 09:16
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _login = MutableStateFlow<UiState<String>>(UiState.Empty)
    val login: StateFlow<UiState<String>> = _login

    fun login(authData: AuthData) {
        if (!hasConnection()) {
            _login.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _login.value = UiState.Loading
            loginUseCase.login(authData).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _login.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _login.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }
}