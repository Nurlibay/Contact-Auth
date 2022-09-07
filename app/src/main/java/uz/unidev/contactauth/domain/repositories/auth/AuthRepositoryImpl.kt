package uz.unidev.contactauth.domain.repositories.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.unidev.contactauth.data.local.LocalDataSource
import uz.unidev.contactauth.data.local.models.AuthData
import uz.unidev.contactauth.data.local.models.toRequest
import uz.unidev.contactauth.data.remote.Networking
import uz.unidev.contactauth.utils.UiState

class AuthRepositoryImpl : AuthRepository {

    private val authApi = Networking.getAuthApi()
    private val localStorage = LocalDataSource.getInstance()

    companion object {
        private var authRepository: AuthRepository? = null
        fun init(){
            if(authRepository == null){
                authRepository = AuthRepositoryImpl()
            }
        }
        fun getInstance() = authRepository!!
    }

    /** register */
    override fun register(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.register(authData.toRequest())
        if(response.isSuccessful){
            val token = response.body()?.data?.token
            localStorage.saveName(authData.name)
            localStorage.savePassword(authData.password)
            localStorage.saveToken(token.toString())
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)

    /** unregister */
    override fun unregister(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.unregister(authData.toRequest())
        if(response.isSuccessful){
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)

    /** login */
    override fun login(authData: AuthData): Flow<UiState<String>> = flow {
        val response = authApi.login(authData.toRequest())
        if(response.isSuccessful) {
            val token = response.body()?.data?.token
            localStorage.saveName(authData.name)
            localStorage.savePassword(authData.password)
            localStorage.saveToken(token.toString())
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)

    /** logout */
    override fun logout(authData: AuthData) : Flow<UiState<String>> = flow {
        val response = authApi.logout(authData.toRequest())
        if(response.isSuccessful) {
            emit(UiState.Loading)
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)
}