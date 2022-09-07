package uz.unidev.contactauth.domain.repositories.contact

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.data.remote.response.ContactResponse
import uz.unidev.contactauth.utils.UiState

interface ContactRepository {
    fun getAllContacts(): Flow<UiState<List<ContactResponse>>>
    fun addContact(contactRequest: ContactRequest): Flow<UiState<String>>
    fun updateContact(id: Int, contactRequest: ContactRequest): Flow<UiState<String>>
    fun deleteContact(contactResponse: ContactResponse): Flow<UiState<String>>
}