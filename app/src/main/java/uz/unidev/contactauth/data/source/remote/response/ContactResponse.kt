package uz.unidev.contactauth.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *  Created by Nurlibay Koshkinbaev on 12/09/2022 13:49
 */

@Parcelize
data class ContactResponse(
    val id: Int,
    val name: String,
    val phone: String
): Parcelable