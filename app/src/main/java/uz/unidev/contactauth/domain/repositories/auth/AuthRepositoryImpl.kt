package uz.unidev.contactauth.domain.repositories.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.unidev.contactauth.data.models.AuthData
import uz.unidev.contactauth.data.models.toRequest
import uz.unidev.contactauth.data.source.local.SharePref
import uz.unidev.contactauth.data.source.remote.api.AuthApi
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    private val sharePref = SharePref.getInstance()

    /** register */
    override fun register(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.register(authData.toRequest())
        if (response.isSuccessful) {
            val token = response.body()?.data?.token
            sharePref.saveName(authData.name)
            sharePref.savePassword(authData.password)
            sharePref.saveToken(token.toString())
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    /** unregister */
    override fun unregister(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.unregister(authData.toRequest())
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    /** login */
    override fun login(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.login(authData.toRequest())
        if (response.isSuccessful) {
            val token = response.body()?.data?.token
            sharePref.saveName(authData.name)
            sharePref.savePassword(authData.password)
            sharePref.saveToken(token.toString())
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    /** logout */
    override fun logout(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.logout(authData.toRequest())
        emit(UiState.Loading)
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}