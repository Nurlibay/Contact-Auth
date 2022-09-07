package uz.unidev.contactauth.domain.usecases.contact.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.domain.repositories.contact.ContactRepository
import uz.unidev.contactauth.domain.usecases.contact.UpdateContactUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class UpdateContactUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : UpdateContactUseCase {
    override fun updateContact(id: Int, contactRequest: ContactRequest): Flow<UiState<String>> {
        return contactRepository.updateContact(id, contactRequest)
    }
}