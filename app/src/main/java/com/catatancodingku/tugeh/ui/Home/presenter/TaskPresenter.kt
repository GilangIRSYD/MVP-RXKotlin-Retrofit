package com.catatancodingku.tugeh.ui.Home.presenter

import com.catatancodingku.tugeh.network.ApiClient
import com.catatancodingku.tugeh.ui.Home.model.TaskModelResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskPresenter(
    val taskView : TaskView
) {
    lateinit var onClickItem: OnClickItem

    @JvmName("setOnClickItem1")
    fun setOnClickItem(onClickItem: OnClickItem){
        this.onClickItem = onClickItem
    }
    fun getDataApi(){

        ApiClient.SERVICE.getTask().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isConnect!!){
                    val data = it.data
                    val completeTask = it.completeTask
                    val uncompletedTask = it.uncompletedTask

                    taskView.onSuccess(data,completeTask!!,uncompletedTask!!)
                }
            },{
                taskView.onFailed("failed : ${it.message}")
            })



//        ApiClient.SERVICE.getTask().enqueue(object : Callback<TaskModelResponse> {
//            override fun onResponse(call: Call<TaskModelResponse>, response: Response<TaskModelResponse>) {
//
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    val data = responseBody?.data
//                    val completeTask = responseBody?.completeTask
//                    val uncompleteTask  = responseBody?.uncompletedTask
//
//                    taskView.onSuccess(data,completeTask!!, uncompleteTask!!)
//
//                }else{
//                    taskView.onFailed("request code 100")
//                }
//            }
//
//            override fun onFailure(call: Call<TaskModelResponse>, t: Throwable) {
//                taskView.onFailed("failed : " + t.localizedMessage)
//            }
//        })
    }

    fun deleteItemTask(id : String?){

        ApiClient.SERVICE.deleteItemTask(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccess!!){
                        onClickItem.onDelete(it.isSuccess!!,it.message!!)
                    }else   {
                        onClickItem.onDelete(it.isSuccess!!,it.message!!)
                    }
                },{
                    onClickItem.onDelete(false,it.localizedMessage!!)
                }
            )

    }

    fun updateStatus (id : String, status : String){
        var message = ""
        when (status){

            "complete" -> message = "Tugas Selesai"
            "uncomplete" -> message = "Hmmm.."
        }
        ApiClient.SERVICE.changeStatusItem(id,status).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccess!!){
                    onClickItem.onChange(it.isSuccess!!,message)
                }else   {
                    onClickItem.onChange(it.isSuccess!!,it.message!!)
                }
            },{
                onClickItem.onChange(false,it.localizedMessage!!)
            })
    }
}