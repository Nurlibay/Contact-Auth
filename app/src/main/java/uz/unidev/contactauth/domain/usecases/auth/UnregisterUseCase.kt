package uz.unidev.contactauth.domain.usecases.auth

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.utils.UiState

interface UnregisterUseCase {
    fun unregister(authData: AuthData): Flow<UiState<String>>
}