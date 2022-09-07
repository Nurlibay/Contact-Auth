package uz.unidev.contactauth.data.local

import android.content.Context
import uz.unidev.contactauth.utils.Constants.KEY_SIGNED_IN
import uz.unidev.contactauth.utils.Constants.LOCAL
import uz.unidev.contactauth.utils.Constants.NAME
import uz.unidev.contactauth.utils.Constants.PASSWORD
import uz.unidev.contactauth.utils.Constants.TOKEN

class LocalDataSource private constructor(context: Context) {
    private val sharedPref = context.getSharedPreferences(LOCAL, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun getToken(): String? = sharedPref.getString(TOKEN, null)
    fun saveToken(token: String) = editor.putString(TOKEN, token).apply()

    fun getName(): String? = sharedPref.getString(NAME, null)
    fun saveName(name: String) = editor.putString(NAME, name).apply()

    fun getPassword(): String? = sharedPref.getString(PASSWORD, null)
    fun savePassword(password: String) = editor.putString(PASSWORD, password).apply()

    var isSigned: Boolean
        get() = sharedPref.getBoolean(KEY_SIGNED_IN, false)
        set(value) = editor.putBoolean(KEY_SIGNED_IN, value).apply()

    companion object {
        private var localDataSource: LocalDataSource? = null

        fun init(context: Context) {
            if (localDataSource == null) {
                localDataSource = LocalDataSource(context)
            }
        }

        fun getInstance() = localDataSource!!
    }
}