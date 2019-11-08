package com.example.achill.repository

import com.example.achill.model.Result
import com.example.achill.data.LoginDataSource
import com.example.achill.model.Gender
import com.example.achill.model.Type
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

    fun register(mail: String,
                 password: String,
                 username: String,
                 type: Type,
                 gender: Gender
    ): Result<User> {
        return loginDataSource.register(mail, password, username, type, gender)
    }

    fun logout() {
        user = null
        loginDataSource.logout()
    }

    private fun setLoggedInUser(loggedInUser: User) {
        user = loggedInUser
        // TODO: 阅读
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}