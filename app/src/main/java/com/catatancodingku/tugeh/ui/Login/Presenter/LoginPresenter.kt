package com.catatancodingku.tugeh.ui.Login.Presenter

import com.catatancodingku.tugeh.network.ApiClient
import com.catatancodingku.tugeh.ui.Login.Model.UserLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(
    var loginView: LoginView
){

    fun getUserLogin(email : String , password : String){

        if (email.isEmpty() || password.isEmpty()){
            loginView.onFieldEmpty("Harap isi semua form")
            return
        }

        ApiClient.SERVICE.getUserLogin(email,password).enqueue(object : Callback<UserLoginResponse>{
            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {

                if (response.isSuccessful){
                    val responseBody = response.body()
                    val isSuccess    = responseBody?.isSuccess
                    val user    = responseBody?.message
                    loginView.onUserLogin(isSuccess!!,user!!)

                }else{

                    loginView.onFailed("Check your link path API")
                }

            }

            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {

                loginView.onFailed("failed: " + t.localizedMessage)
            }
        })
    }

}
