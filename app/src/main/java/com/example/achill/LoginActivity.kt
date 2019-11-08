package com.example.achill

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.achill.ui.login.LoginViewModel
import com.example.achill.ui.login.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val model by lazy {
        ViewModelProviders.of(this,
            LoginViewModelFactory()
        )[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        model.logInResult.observe(this, Observer {
            if (it.success != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }

            if (it.failure != null) {
                when (it.failure) {
                    1 ->  Snackbar.make(findViewById(R.id.login_layout), "邮箱地址格式错误", Snackbar.LENGTH_LONG).show()
                    2 ->  Snackbar.make(findViewById(R.id.login_layout), "密码过短", Snackbar.LENGTH_LONG).show()
                    400 ->  Snackbar.make(findViewById(R.id.login_layout), "登入失败", Snackbar.LENGTH_LONG).show()
                    else -> Snackbar.make(findViewById(R.id.login_layout), "未知错误", Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    fun loginOnClick(view: View) {
        model.login(login_mail.text.toString(), login_password.text.toString())
    }

    fun registerOnClick(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
