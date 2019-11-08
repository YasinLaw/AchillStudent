package com.example.achill.ui.login

import com.example.achill.model.Type

data class LoggedInUserView(
    val username: String,
    val mail: String,
    val type: Type
)