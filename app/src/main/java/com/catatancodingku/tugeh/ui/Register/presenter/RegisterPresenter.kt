package com.catatancodingku.tugeh.ui.Register.presenter

import com.catatancodingku.tugeh.network.ApiClient
import com.catatancodingku.tugeh.ui.Register.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(
    val registerView: RegisterView
) {

    fun registerAccount(
        firstName : String,
        lastName : String,
        email : String,
        password : String
    ){
        ApiClient.SERVICE.createAccount(firstName,lastName,email,password)
            .enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body?.isSuccess!!){
                            registerView.onSuccessRegister(body.message!!)
                        }else {
                            registerView.onFailedRegister(body.message!!)
                        }
                    }else {
                        registerView.onFailedRegister("Periksa path URL web Service")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    registerView.onFailedRegister("on Failure : " + t.localizedMessage)
                }
            })
    }
}