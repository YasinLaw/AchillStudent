package com.example.achill.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.achill.model.Result
import com.example.achill.repository.LoginRepository

class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LogInResult>()
    val logInResult: LiveData<LogInResult> = _loginResult

    fun login(mail: String, password: String) {

        if (!isMailValid(mail)) {
            _loginResult.postValue(LogInResult(failure = 1))
            return
        }

        if (!isPasswordValid(password)) {
            _loginResult.postValue(LogInResult(failure = 2))
            return
        }

        var result = loginRepository.login(mail, password)

        if (result is Result.Success) {
            _loginResult.postValue(LogInResult(
                success = LoggedInUserView(
                    username = result.data.username,
                    mail = result.data.mail,
                    type = result.data.type
                )))
        } else {
            _loginResult.postValue(LogInResult(failure = 400))
        }
    }

    private fun isMailValid(mail: String): Boolean {
        if (mail.contains('@')) {
            return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        }
        return false
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}