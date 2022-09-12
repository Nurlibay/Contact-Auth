package uz.unidev.contactauth.domain.usecases.contact

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.utils.UiState

/**
 *  Created by Nurlibay Koshkinbaev on 08/09/2022 01:04
 */

interface GetAllContactsUseCase {
    fun getAllContacts(): Flow<UiState<List<ContactResponse>>>
}