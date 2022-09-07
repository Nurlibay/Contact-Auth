package uz.unidev.contactauth.domain.usecases.contact.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.domain.repositories.contact.ContactRepository
import uz.unidev.contactauth.domain.usecases.contact.AddContactUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class AddContactUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : AddContactUseCase {
    override fun addContact(contactRequest: ContactRequest): Flow<UiState<String>> {
        return contactRepository.addContact(contactRequest)
    }
}