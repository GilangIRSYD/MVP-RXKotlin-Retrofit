package com.catatancodingku.tugeh.ui.Add.presenter

interface AddTaskView {

    fun onSuccessAddTask(isSuccess : Boolean,msg : String)
    fun onFailedAddTask(msg: String)
}