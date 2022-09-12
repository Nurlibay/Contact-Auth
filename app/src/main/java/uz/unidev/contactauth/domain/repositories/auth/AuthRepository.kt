package uz.unidev.contactauth.domain.repositories.auth

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.utils.UiState

interface AuthRepository {
    fun register(authData: AuthData): Flow<UiState<String>>
    fun unregister(authData: AuthData): Flow<UiState<String>>
    fun login(authData: AuthData): Flow<UiState<String>>
    fun logout(authData: AuthData): Flow<UiState<String>>
}