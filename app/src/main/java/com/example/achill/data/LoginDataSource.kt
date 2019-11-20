package com.example.achill.data

import com.example.achill.model.Gender
import com.example.achill.model.Result
import com.example.achill.model.Type
import com.example.achill.model.User

class LoginDataSource {
    fun login(username: String, password: String): Result<User> {
        return try {
            // TODO: 储存token
            val loggedInUser = User(username = "Dev",
                type = Type.UNKNOWN,
                mail = "yasinlaw@outlook.com")
            Result.Success(loggedInUser)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    fun register(mail: String,
                 password: String,
                 username: String,
                 type: Type,
                 gender: Gender): Result<User> {
        return try {
            val registerUser = User(username = username,
                mail = mail,
                type = type,
                gender = gender)
            fakeOp(registerUser, password)
            Result.Success(registerUser)
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    private fun fakeOp(user: User, password: String): Boolean {
        return true
    }

    fun logout() {
        // TODO: 抛弃token
    }
}