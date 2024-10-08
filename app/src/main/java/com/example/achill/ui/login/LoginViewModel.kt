package com.example.achill.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.achill.model.Gender
import com.example.achill.model.Result
import com.example.achill.model.Type
import com.example.achill.repository.LoginRepository

class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<LogInResult>()
    val logInResult: LiveData<LogInResult> = _loginResult

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun login(mail: String, password: String) {

        if (!isMailValid(mail)) {
            _loginResult.postValue(LogInResult(failure = "邮箱地址格式错误"))
            return
        }

        if (!isPasswordValid(password)) {
            _loginResult.postValue(LogInResult(failure = "密码不合法"))
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
            _loginResult.postValue(LogInResult(failure = result.toString()))
        }
    }

    fun register(mail: String,
                 password: String,
                 username: String,
                 type: Type,
                 gender: Gender) {
        if (!isMailValid(mail)) {
            _registerResult.postValue(RegisterResult(failure = "邮箱地址格式错误"))
            return
        }
        if (!isUsernameValid(username)) {
            _registerResult.postValue(RegisterResult(failure = "用户名不合法"))
            return
        }
        if (!isPasswordValid(password)) {
            _registerResult.postValue(RegisterResult(failure = "密码不合法"))
            return
        }
        if (!isGenderValid(gender)) {
            _registerResult.postValue(RegisterResult(failure = "性别未知"))
            return
        }
        if (!isTypeValid(type)) {
            _registerResult.postValue(RegisterResult(failure = "身份未知"))
            return
        }
        var result = loginRepository.register(mail, password, username, type, gender)
        if (result is Result.Success) {
            _registerResult.postValue(
                RegisterResult(
                success = true
            ))
        } else {
            _registerResult.postValue(RegisterResult(failure = result.toString()))
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

    private fun isGenderValid(gender: Gender): Boolean {
        return gender != Gender.UNKNOWN
    }

    private fun isTypeValid(type: Type): Boolean {
        return type != Type.UNKNOWN
    }

    private fun isUsernameValid(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9]{5,16}$")
        return regex.matches(username)
    }
}