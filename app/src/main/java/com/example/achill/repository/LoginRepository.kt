package com.example.achill.repository

import com.example.achill.model.Result
import com.example.achill.data.LoginDataSource
import com.example.achill.model.User

class LoginRepository(val loginDataSource: LoginDataSource) {
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    fun login(mail: String, password: String): Result<User> {
        val result = loginDataSource.login(mail, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun logout() {
        user = null
        loginDataSource.logout()
    }

    private fun setLoggedInUser(loggedInUser: User) {
        this.user = loggedInUser
        // TODO: 阅读
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}