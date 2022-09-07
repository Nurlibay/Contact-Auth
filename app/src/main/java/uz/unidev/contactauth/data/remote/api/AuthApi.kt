package uz.unidev.contactauth.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.unidev.contactauth.data.remote.request.AuthRequest
import uz.unidev.contactauth.data.remote.response.AuthResponse
import uz.unidev.contactauth.data.remote.response.BaseResponse

interface AuthApi {

    @POST("register")
    suspend fun register(@Body authRequest: AuthRequest): Response<BaseResponse<AuthResponse>>

    @POST("unregister")
    suspend fun unregister(@Body authRequest: AuthRequest): Response<BaseResponse<AuthResponse>>

    @POST("login")
    suspend fun login(@Body authRequest: AuthRequest): Response<BaseResponse<AuthResponse>>

    @POST("logout")
    suspend fun logout(@Body authRequest: AuthRequest): Response<BaseResponse<AuthResponse>>

}