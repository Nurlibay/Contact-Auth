package uz.unidev.contactauth.domain.usecases.contact.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.remote.response.ContactResponse
import uz.unidev.contactauth.domain.repositories.contact.ContactRepository
import uz.unidev.contactauth.domain.usecases.contact.GetAllContactsUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class GetAllContactsUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : GetAllContactsUseCase {
    override fun getAllContacts(): Flow<UiState<List<ContactResponse>>> {
        return contactRepository.getAllContacts()
    }
}