package uz.unidev.contactauth.domain.usecases.contact

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.utils.UiState

interface UpdateContactUseCase {
    fun updateContact(id: Int, contactRequest: ContactRequest): Flow<UiState<String>>
}