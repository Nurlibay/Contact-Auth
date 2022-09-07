package uz.unidev.contactauth.data.local.models

import uz.unidev.contactauth.data.remote.request.AuthRequest

/**
 *  Created by Nurlibay Koshkinbaev on 12/08/2022 16:44
 */

data class AuthData(
    val name: String,
    val password: String
)

fun AuthData.toRequest() = AuthRequest(name, password)