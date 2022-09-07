package uz.unidev.contactauth.domain.usecases.auth.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.domain.repositories.auth.AuthRepository
import uz.unidev.contactauth.domain.usecases.auth.LogoutUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LogoutUseCase {
    override fun logout(authData: AuthData): Flow<UiState<String>> {
        return authRepository.logout(authData)
    }
}