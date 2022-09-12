package uz.unidev.contactauth.data.source.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.unidev.contactauth.data.source.remote.request.ContactRequest
import uz.unidev.contactauth.data.source.remote.response.BaseResponse
import uz.unidev.contactauth.data.source.remote.response.ContactResponse

interface ContactApi {

    @GET("contact")
    suspend fun getContact(
        @Header("token") token: String,
        @Query("id") id: Int
    ): Response<BaseResponse<ContactResponse>>

    @GET("contact")
    suspend fun getAllContacts(
        @Header("token") token: String
    ): Response<BaseResponse<List<ContactResponse>>>

    @POST("contact")
    suspend fun addContact(
        @Header("token") token: String,
        @Body contactRequest: ContactRequest
    ): Response<BaseResponse<ContactResponse>>

    @PUT("contact")
    suspend fun updateContact(
        @Header("token") token: String,
        @Query("id") id: Int,
        @Body contactRequest: ContactRequest
    ): Response<BaseResponse<ContactResponse>>

    @DELETE("contact")
    suspend fun deleteContact(
        @Header("token") token: String,
        @Query("id") id: Int
    ): Response<BaseResponse<ContactResponse>>
}