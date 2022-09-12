package uz.unidev.contactauth.domain.usecases.contact

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.utils.UiState

interface DeleteContactUseCase {
    fun deleteContact(contactResponse: ContactResponse): Flow<UiState<String>>
}