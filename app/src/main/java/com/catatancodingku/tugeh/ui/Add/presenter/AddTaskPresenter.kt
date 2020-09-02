package com.catatancodingku.tugeh.ui.Add.presenter

import com.catatancodingku.tugeh.network.ApiClient
import com.catatancodingku.tugeh.ui.Add.model.AddTaskActionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTaskPresenter(
    val addTaskView: AddTaskView
) {

    fun addTask(title : String, desc : String, author : String = "Anonym", status : String = "uncomplete"){

        ApiClient.SERVICE.addTask(title, desc, author, status)
            .enqueue(object : Callback<AddTaskActionResponse>{
                override fun onResponse(
                    call: Call<AddTaskActionResponse>,
                    response: Response<AddTaskActionResponse>
                ) {
                    if (response.isSuccessful){
                        if (response.body()?.isSuccess!!){
                            addTaskView.onSuccessAddTask(response.body()?.isSuccess!!,response.body()?.message!!)
                        }
                    }else{
                        addTaskView.onFailedAddTask("Periksa URL Service ada masalah di server")
                    }
                }

                override fun onFailure(call: Call<AddTaskActionResponse>, t: Throwable) {
                    addTaskView.onFailedAddTask("failed  : ${t.localizedMessage}")
                }
            })
    }
}