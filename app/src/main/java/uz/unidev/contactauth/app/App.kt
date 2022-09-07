package uz.unidev.contactauth.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.unidev.contactauth.data.local.LocalDataSource
import uz.unidev.contactauth.domain.repositories.auth.AuthRepositoryImpl
import uz.unidev.contactauth.domain.repositories.contact.ContactRepositoryImpl

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        LocalDataSource.init(instance)
        AuthRepositoryImpl.init()
        ContactRepositoryImpl.init()
    }

    companion object {
        lateinit var instance: App
    }
}