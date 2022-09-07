package uz.unidev.contactauth.domain.usecases.contact

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.utils.UiState

interface AddContactUseCase {
    fun addContact(contactRequest: ContactRequest): Flow<UiState<String>>
}