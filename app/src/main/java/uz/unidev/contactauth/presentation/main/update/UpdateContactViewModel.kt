package uz.unidev.contactauth.presentation.main.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.unidev.contactauth.data.source.remote.request.ContactRequest
import uz.unidev.contactauth.domain.usecases.contact.UpdateContactUseCase
import uz.unidev.contactauth.utils.UiState
import uz.unidev.contactauth.utils.hasConnection
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 05/09/2022 11:23
 */

@HiltViewModel
class UpdateContactViewModel @Inject constructor(
    private val updateContactUseCase: UpdateContactUseCase
): ViewModel() {

    private val _update = MutableStateFlow<UiState<String>>(UiState.Empty)
    val update: StateFlow<UiState<String>> = _update

    fun updateContact(id: Int, contactRequest: ContactRequest) {
        if (!hasConnection()) {
            _update.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _update.value = UiState.Loading
            updateContactUseCase.updateContact(id, contactRequest).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _update.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _update.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }
}