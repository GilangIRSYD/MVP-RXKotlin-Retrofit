package com.catatancodingku.tugeh.ui.edit.presenter

import com.catatancodingku.tugeh.network.ApiClient
import com.catatancodingku.tugeh.ui.edit.view.EditView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class EditPresenter(
    val editView: EditView
) {

    fun editItemTask(id : String,title : String, desc : String, author : String, status : String){

        ApiClient.SERVICE.updateItemTask(id, title, desc, author, status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                editView.onSuccessUpdateItem(it.isSuccess!!,it.message!!)
            },{
                editView.onFailedtoUpdate("error : ${it.localizedMessage}")
            })
    }

    fun deleteItemTask (id : String){

        ApiClient.SERVICE.deleteItemTask(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    editView.onSuccessDeleteItem(it.isSuccess!!,it.message!!)
                },
                {
                    editView.onFailedtoUpdate("error : ${it.localizedMessage}")
                }
            )
    }
}