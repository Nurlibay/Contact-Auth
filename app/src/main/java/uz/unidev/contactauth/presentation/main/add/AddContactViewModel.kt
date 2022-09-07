package uz.unidev.contactauth.presentation.main.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.domain.repositories.contact.ContactRepositoryImpl
import uz.unidev.contactauth.domain.usecases.contact.AddContactUseCase
import uz.unidev.contactauth.utils.UiState
import uz.unidev.contactauth.utils.hasConnection
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 05/09/2022 10:58
 */

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase
): ViewModel() {

    private val _addContact = MutableStateFlow<UiState<String>>(UiState.Empty)
    val addContact: StateFlow<UiState<String>> = _addContact

    fun addContact(contactRequest: ContactRequest) {
        if (!hasConnection()) {
            _addContact.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _addContact.value = UiState.Loading
            addContactUseCase.addContact(contactRequest).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _addContact.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _addContact.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }

}