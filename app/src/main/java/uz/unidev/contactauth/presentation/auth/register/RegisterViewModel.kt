package uz.unidev.contactauth.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.domain.repositories.auth.AuthRepositoryImpl
import uz.unidev.contactauth.domain.usecases.auth.RegisterUseCase
import uz.unidev.contactauth.utils.UiState
import uz.unidev.contactauth.utils.hasConnection

/**
 *  Created by Nurlibay Koshkinbaev on 05/09/2022 11:13
 */

class RegisterViewModel constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _register = MutableStateFlow<UiState<String>>(UiState.Empty)
    val register: StateFlow<UiState<String>> = _register

    fun register(authData: AuthData) {
        if (!hasConnection()) {
            _register.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _register.value = UiState.Loading
            registerUseCase.register(authData).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _register.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _register.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }

}