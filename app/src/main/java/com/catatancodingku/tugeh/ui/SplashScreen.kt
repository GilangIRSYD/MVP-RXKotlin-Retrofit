package com.catatancodingku.tugeh.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.helper.SessionManager
import com.catatancodingku.tugeh.ui.Home.view.MainActivity
import com.catatancodingku.tugeh.ui.Login.View.LoginAct

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val mhandler = Handler()
        val sessionManager = SessionManager(this)

        mhandler.postDelayed(Runnable {
            if (sessionManager.login!!) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginAct::class.java))
                finish()
            }
        }, 2500)
    }
}