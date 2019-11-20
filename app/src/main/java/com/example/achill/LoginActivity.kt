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

    private val model by lazy {
        ViewModelProviders.of(this, LoginViewModelFactory())[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        model.logInResult.observe(this, Observer {
            if (it.success != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            if (it.failure != null) {
                Snackbar.make(login_layout, it.failure, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    fun loginOnClick(view: View) {
        model.login(login_username.text.toString(), login_password.text.toString())
    }

    fun registerOnClick(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        if (login_username.text != null) {
            intent.putExtra("mail", login_username.text.toString())
        }
        if (login_password.text != null) {
            intent.putExtra("password", login_password.text.toString())
        }
        startActivity(intent)
    }
}
