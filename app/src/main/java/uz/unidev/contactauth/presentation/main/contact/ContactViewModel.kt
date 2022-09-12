package uz.unidev.contactauth.presentation.main.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.domain.usecases.auth.LogoutUseCase
import uz.unidev.contactauth.domain.usecases.auth.UnregisterUseCase
import uz.unidev.contactauth.domain.usecases.contact.DeleteContactUseCase
import uz.unidev.contactauth.domain.usecases.contact.GetAllContactsUseCase
import uz.unidev.contactauth.utils.UiState
import uz.unidev.contactauth.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val unregisterUseCase: UnregisterUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModel() {

    private val _contacts = MutableStateFlow<UiState<List<ContactResponse>>>(UiState.Empty)
    val contacts: StateFlow<UiState<List<ContactResponse>>> = _contacts

    fun getAllContacts() {
        if (!hasConnection()) {
            _contacts.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _contacts.value = UiState.Loading
            getAllContactsUseCase.getAllContacts().collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _contacts.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _contacts.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }

    private val _logout = MutableStateFlow<UiState<String>>(UiState.Empty)
    val logout: StateFlow<UiState<String>> = _logout

    fun logout(authData: AuthData) {
        if (!hasConnection()) {
            _logout.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _logout.value = UiState.Loading
            logoutUseCase.logout(authData).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _logout.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _logout.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }

    private val _unregister = MutableStateFlow<UiState<String>>(UiState.Empty)
    val unregister: StateFlow<UiState<String>> = _unregister

    fun unregister(authData: AuthData) {
        if (!hasConnection()) {
            _unregister.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _unregister.value = UiState.Loading
            unregisterUseCase.unregister(authData).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _unregister.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _unregister.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }

    private val _deleteContact = MutableStateFlow<UiState<String>>(UiState.Empty)
    val deleteContact: StateFlow<UiState<String>> = _deleteContact

    fun deleteContact(contactResponse: ContactResponse) {
        if (!hasConnection()) {
            _deleteContact.value = UiState.NetworkError("No Internet Connection!")
            return
        }
        viewModelScope.launch {
            _deleteContact.value = UiState.Loading
            deleteContactUseCase.deleteContact(contactResponse).collect {
                when (it) {
                    is UiState.Success -> {
                        val result = it.data
                        _deleteContact.value = UiState.Success(result)
                    }
                    is UiState.Error -> {
                        _deleteContact.value = UiState.Error(it.msg)
                    }
                }
            }
        }
    }
}