package com.sokamn.logininstagram.login.data

import com.sokamn.logininstagram.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {

    suspend fun doLogin(user: String, password: String): Boolean {
        return api.doLogin(user, password)
    }
}