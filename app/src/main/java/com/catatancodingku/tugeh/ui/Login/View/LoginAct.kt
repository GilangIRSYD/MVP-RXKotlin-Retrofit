package com.catatancodingku.tugeh.ui.Login.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.helper.SessionManager
import com.catatancodingku.tugeh.ui.Home.view.MainActivity
import com.catatancodingku.tugeh.ui.Login.Presenter.LoginPresenter
import com.catatancodingku.tugeh.ui.Login.Presenter.LoginView
import com.catatancodingku.tugeh.ui.Register.view.RegisterAct
import kotlinx.android.synthetic.main.activity_login.*

class LoginAct : AppCompatActivity(), LoginView {
    private val TAG     = "RESPONSEUSER"
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginPresenter = LoginPresenter(this)
        sessionManager = SessionManager(this)

        btn_login.setOnClickListener {
            val email = ed_email.editText!!.text.toString()
            val password = ed_password.editText!!.text.toString()

            Log.d(TAG, "email,password ($email, $password) ")
            loginPresenter.getUserLogin(email,password)

        }

        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterAct::class.java))
        }

    }



    override fun onUserLogin(isSuccess: Boolean, user: String) {
        if (isSuccess){
            Toast.makeText(this, "Selamat Datang,  ${user.split("&split&")[0]}", Toast.LENGTH_SHORT).show()

            val author = user.split("&split&")[0]
            val lastName = user.split("&split&")[1]

            sessionManager.author = author
            sessionManager.lastName = lastName
            sessionManager.fullname = "$author $lastName"
            sessionManager.login = true

            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }else   {
            Toast.makeText(this, "Email Password Invalid", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailed(msg: String) {
        Log.e(TAG, "OnFailed: $msg")
    }

    override fun onFieldEmpty(msg: String) {
        Toast.makeText(this, " $msg", Toast.LENGTH_SHORT).show()
    }
}