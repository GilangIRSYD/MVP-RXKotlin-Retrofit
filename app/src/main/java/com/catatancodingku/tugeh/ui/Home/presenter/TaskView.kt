package com.catatancodingku.tugeh.ui.Home.presenter

import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel

interface TaskView {

    fun onSuccess(data: List<DataTaskModel>?, completeTask : Int, uncompleteTask : Int)
    fun onFailed(msg : String)
}