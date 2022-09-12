package uz.unidev.contactauth.domain.usecases.auth.impl

import kotlinx.coroutines.flow.Flow
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.domain.repositories.auth.AuthRepository
import uz.unidev.contactauth.domain.usecases.auth.LoginUseCase
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : LoginUseCase {
    override fun login(authData: AuthData): Flow<UiState<String>> {
        return authRepository.login(authData)
    }
}