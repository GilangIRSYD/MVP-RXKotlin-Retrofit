package com.catatancodingku.tugeh.ui.Login.Presenter

import com.catatancodingku.tugeh.ui.Login.Model.UserLoginResponse

interface LoginView {
    fun onUserLogin(isSuccess : Boolean, user : String)
    fun onFailed(msg : String)
    fun onFieldEmpty(msg : String)
}