package com.catatancodingku.tugeh.ui.Register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.ui.Register.presenter.RegisterPresenter
import com.catatancodingku.tugeh.ui.Register.presenter.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterAct : AppCompatActivity(), RegisterView {

    private val TAG = "RESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerPresenter = RegisterPresenter(this)


        btn_register_account.setOnClickListener {
            val firstName = ed_first_name.editText!!.text.toString()
            val lastName = ed_last_name.editText!!.text.toString()
            val email = ed_email_register.editText!!.text.toString()
            val password = ed_password_register.editText!!.text.toString()
            val confirmPass = ed_confirm_password_register.editText!!.text.toString()



            if (validateForm(firstName, lastName, email, password, confirmPass)) {
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                registerPresenter.registerAccount(firstName, lastName, email, password)
                finish()
            } else {
                Toast.makeText(this, "Failed to Register", Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun validateForm(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPass: String
    ): Boolean {
        var isValid = true

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {


        }

        if (firstName.isEmpty()) {
            ed_first_name.error = "Form tidak boleh kosong"
            isValid = false
        } else {
            ed_first_name.error = null
        }

        if (lastName.isEmpty()) {
            ed_last_name.error = "Form tidak boleh kosong"
            isValid = false
        } else {
            ed_last_name.error = null
        }
        if (email.isEmpty()) {
            ed_email_register.error = "Form tidak boleh kosong"
            isValid = false
        } else {
            ed_email_register.error = null
        }
        if (password.isEmpty()) {
            ed_password_register.error = "Form tidak boleh kosong"
            isValid = false
        } else {
            ed_password_register.error = null
        }
        if (confirmPass.isEmpty()){
            ed_confirm_password_register.error = "Tidak boleh kosong"
        }else{
            ed_confirm_password_register.error = null
        }
        if (password != confirmPass) {
            ed_confirm_password_register.error = "Password tidak cocok"
            isValid = false
        }else{
            ed_confirm_password_register.error  = null
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            ed_email_register.error = "Masukkan email dengan benar"
            isValid = false
        }else {
            ed_email_register.error = null
        }

        return isValid


    }

    override fun onSuccessRegister(msg: String) {
        Log.d(TAG, "onSuccessRegister: $msg")
    }

    override fun onFailedRegister(msg: String) {
        Log.e(TAG, "onFailedRegister: $msg" )
    }
}