package uz.unidev.contactauth.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactResponse(
    val id: Int,
    val name: String,
    val phone: String
) : Parcelable