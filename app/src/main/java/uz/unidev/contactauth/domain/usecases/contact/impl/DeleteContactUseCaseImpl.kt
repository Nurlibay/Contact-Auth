package uz.unidev.contactauth.domain.usecases.contact.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.response.ContactResponse
import uz.unidev.contactauth.domain.repositories.contact.ContactRepository
import uz.unidev.contactauth.domain.usecases.contact.DeleteContactUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class DeleteContactUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : DeleteContactUseCase {
    override fun deleteContact(contactResponse: ContactResponse): Flow<UiState<String>> {
        return contactRepository.deleteContact(contactResponse)
    }
}