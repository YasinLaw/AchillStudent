package com.example.achill.ui.login

data class LogInResult(
    val success: LoggedInUserView? = null,
    val failure: String? = null
)