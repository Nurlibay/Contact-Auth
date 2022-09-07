package uz.unidev.contactauth.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.unidev.contactauth.data.remote.api.AuthApi
import uz.unidev.contactauth.data.remote.api.ContactApi

class Networking {
    companion object {
        private const val BASE_URL = "https://0e5d-185-213-230-111.in.ngrok.io"

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        private val client = OkHttpClient.Builder().addInterceptor(logging).build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        fun getAuthApi(): AuthApi = retrofit.create(AuthApi::class.java)
        fun getContactApi(): ContactApi = retrofit.create(ContactApi::class.java)
    }
}