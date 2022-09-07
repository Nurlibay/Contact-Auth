package uz.unidev.contactauth.domain.repositories.contact

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.unidev.contactauth.data.local.LocalDataSource
import uz.unidev.contactauth.data.remote.Networking
import uz.unidev.contactauth.data.remote.request.ContactRequest
import uz.unidev.contactauth.data.remote.response.ContactResponse
import uz.unidev.contactauth.utils.UiState

class ContactRepositoryImpl : ContactRepository {

    private val contactApi = Networking.getContactApi()
    private val localStorage = LocalDataSource.getInstance()

    companion object {
        private var contactRepository: ContactRepository? = null
        fun init() {
            if (contactRepository == null) {
                contactRepository = ContactRepositoryImpl()
            }
        }

        fun getInstance() = contactRepository!!
    }

    /** get all contacts */
    override fun getAllContacts(): Flow<UiState<List<ContactResponse>>> = flow {
        val response = contactApi.getAllContacts(localStorage.getToken().toString())
        if (response.isSuccessful) {
            emit(UiState.Loading)
            emit(UiState.Success(response.body()?.data!!))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)

    /** add contact */
    override fun addContact(contactRequest: ContactRequest): Flow<UiState<String>> = flow {
        val response = contactApi.addContact(localStorage.getToken().toString(), contactRequest)
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)


    /** update contact */
    override fun updateContact(id: Int, contactRequest: ContactRequest): Flow<UiState<String>> = flow {
        val response = contactApi.updateContact(localStorage.getToken().toString(), id, contactRequest)
        if (response.isSuccessful) {
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)

    /** delete contact */
    override fun deleteContact(contactResponse: ContactResponse): Flow<UiState<String>> = flow {
        val response = contactApi.deleteContact(localStorage.getToken().toString(), contactResponse.id)
        if(response.isSuccessful){
            emit(UiState.Success(response.body()?.message.toString()))
        } else {
            emit(UiState.Error(response.errorBody()?.string().toString()))
        }
    }.flowOn(Dispatchers.IO)
}