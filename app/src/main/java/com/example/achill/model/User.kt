package com.example.achill.model

import java.util.*

data class User(private val uid: String = UUID.randomUUID().toString(),
                var username: String,
                var gender: Gender = Gender.UNKNOWN,
                var type: Type = Type.UNKNOWN,
                var mail: String = "")

enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN
}

enum class Type {
    FACULTY,
    STUDENT,
    ADMINISTRATOR,
    UNKNOWN
}