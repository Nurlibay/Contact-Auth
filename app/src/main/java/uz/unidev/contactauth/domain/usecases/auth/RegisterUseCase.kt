package uz.unidev.contactauth.domain.usecases.auth

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.utils.UiState

interface RegisterUseCase {
    fun register(authData: AuthData): Flow<UiState<String>>
}