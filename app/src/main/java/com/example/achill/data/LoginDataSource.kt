package com.example.achill.data

import com.example.achill.model.Result
import com.example.achill.model.Type
import com.example.achill.model.User

class LoginDataSource {
    fun login(mail: String, password: String): Result<User> {
        try {
            // TODO: 储存token
            val loggedInUser = User(username = mail,
                type = Type.UNKNOWN,
                mail = mail)
            return Result.Success(loggedInUser)
        } catch (e: Exception) {
            return Result.Failure(e)
        }
    }

    fun logout() {
        // TODO: 抛弃token
    }
}