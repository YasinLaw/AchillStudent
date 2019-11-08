package com.example.achill.data

import com.example.achill.model.Gender
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

    fun register(mail: String,
                 password: String,
                 username: String,
                 type: Type,
                 gender: Gender): Result<User> {
        try {
            val registerUser = User(username = username,
                mail = mail,
                type = Type.UNKNOWN,
                gender = gender)
            fakeOp(registerUser, password)
            return Result.Success(registerUser)
        } catch (exception: Exception) {
            return Result.Failure(exception)
        }
    }

    fun fakeOp(user: User, password: String): Boolean {
        return true
    }

    fun logout() {
        // TODO: 抛弃token
    }
}