package uz.unidev.contactauth.domain.usecases.auth.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.domain.repositories.auth.AuthRepository
import uz.unidev.contactauth.domain.usecases.auth.UnregisterUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class UnregisterUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : UnregisterUseCase {
    override fun unregister(authData: AuthData): Flow<UiState<String>> {
        return authRepository.unregister(authData)
    }
}