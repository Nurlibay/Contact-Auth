package uz.unidev.contactauth.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.unidev.contactauth.data.source.local.SharePref

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        SharePref.init(this)
    }

    companion object {
        lateinit var instance: App
    }
}