package com.example.achill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.achill.model.Gender
import com.example.achill.model.Result
import com.example.achill.model.Type
import com.example.achill.model.User
import com.example.achill.ui.login.LoginViewModel
import com.example.achill.ui.login.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val model by lazy {
        ViewModelProviders.of(this,
            LoginViewModelFactory()
        )[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        model.registerResult.observe(this, Observer {
            if (it.success != null) {
                finish()
                Snackbar.make(login_layout, "注册成功", Snackbar.LENGTH_LONG).show()
            }
            if (it.failure != null) {
                when (it.failure) {
                    1 ->  Snackbar.make(findViewById(R.id.register_layout), "邮箱地址格式错误", Snackbar.LENGTH_LONG).show()
                    3 -> Snackbar.make(findViewById(R.id.register_layout), "用户名不合法", Snackbar.LENGTH_LONG).show()
                    2 ->  Snackbar.make(findViewById(R.id.register_layout), "密码过短", Snackbar.LENGTH_LONG).show()
                    400 ->  Snackbar.make(findViewById(R.id.register_layout), "登入失败", Snackbar.LENGTH_LONG).show()
                    else -> Snackbar.make(findViewById(R.id.register_layout), "未知错误", Snackbar.LENGTH_LONG).show()
                }
            }
        })
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
