package com.example.achill

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.achill.model.Gender
import com.example.achill.model.Type
import com.example.achill.model.User
import com.example.achill.ui.login.LoginViewModel
import com.example.achill.ui.login.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val model by lazy {
        ViewModelProviders.of(this, LoginViewModelFactory())[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val intent = intent
        if (intent.hasExtra("mail")) {
            register_mail.setText(intent.getStringExtra("mail"))
        }
        if (intent.hasExtra("password")) {
            register_password.setText(intent.getStringExtra("password"))
        }

        model.registerResult.observe(this, Observer {
            if (it.success != null) {
                finish()
            }
            if (it.failure != null) {
                Snackbar.make(register_layout, it.failure, Snackbar.LENGTH_LONG).show()
            }
        })

        register_password.setOnFocusChangeListener { _, hasFocus ->
            run {
                if (hasFocus) {
                    register_password_layout.helperText = "* 密码长度须为6位及以上"
                } else {
                    register_password_layout.helperText = null
                }
            }
        }

        register_username.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    register_username_layout.helperText = "* 用户名长度须为5至16位，合法字符为A-Z、a-z、0-9"
                } else {
                    register_username_layout.helperText = null
                }
            }
        }
    }

    fun registerOnClick(view: View) {
        val user = feedUserData()
        val result = model.register(user.mail,
            register_password.text.toString(),
            user.username,
            user.type,
            user.gender)
    }

    fun feedUserData(): User {
        val user = User(username = register_username.text.toString(),
            mail = register_mail.text.toString())
        if (register_male.isChecked) {
            user.gender = Gender.MALE
        } else if (register_female.isChecked) {
            user.gender = Gender.FEMALE
        }
        if (register_student.isChecked) {
            user.type = Type.STUDENT
        } else if (register_faculty.isChecked) {
            user.type = Type.FACULTY
        }
        return user
    }
}
