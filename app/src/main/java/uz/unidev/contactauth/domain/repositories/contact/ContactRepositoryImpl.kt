package uz.unidev.contactauth.domain.repositories.contact

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.unidev.contactauth.data.source.local.SharePref
import uz.unidev.contactauth.data.source.remote.api.ContactApi
import uz.unidev.contactauth.data.source.remote.request.ContactRequest
import uz.unidev.contactauth.data.source.remote.response.ContactResponse
import uz.unidev.contactauth.utils.UiState
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val contactApi: ContactApi,
) : ContactRepository {

    val sharePref = SharePref.getInstance()

    /** get all contacts */
    override fun getAllContacts(): Flow<UiState<List<ContactResponse>>> = flow {
        val response = contactApi.getAllContacts(sharePref.getToken().toString())
        if (response.isSuccessful) {
            emit(UiState.Loading)
            emit(UiState.Success(response.body()?.data!!))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    /** add contact */
    override fun addContact(contactRequest: ContactRequest): Flow<UiState<String>> = flow {
        val response = contactApi.addContact(sharePref.getToken().toString(), contactRequest)
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


    /** update contact */
    override fun updateContact(id: Int, contactRequest: ContactRequest): Flow<UiState<String>> =
        flow {
            val response =
                contactApi.updateContact(sharePref.getToken().toString(), id, contactRequest)
            if (response.isSuccessful) {
                emit(UiState.Success(response.body()?.message.toString()))
            } else {
                emit(UiState.Error(response.errorBody()?.string().toString()))
            }
        }.catch {
            emit(UiState.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    /** delete contact */
    override fun deleteContact(contactResponse: ContactResponse): Flow<UiState<String>> = flow {
        val response = contactApi.deleteContact(sharePref.getToken().toString(), contactResponse.id)
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.catch {
        emit(UiState.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}