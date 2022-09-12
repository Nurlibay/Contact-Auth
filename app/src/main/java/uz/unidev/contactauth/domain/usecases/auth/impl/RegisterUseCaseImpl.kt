package uz.unidev.contactauth.domain.usecases.auth.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.domain.repositories.auth.AuthRepository
import uz.unidev.contactauth.domain.usecases.auth.RegisterUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RegisterUseCase {
    override fun register(authData: AuthData): Flow<UiState<String>> {
        return authRepository.register(authData)
    }
}