package com.catatancodingku.tugeh.ui.Home.presenter

import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel

interface OnClickItem {
    fun itemClickDelete (id : String?)
    fun onDelete (isSuccess : Boolean, msg : String)
    fun itemClick (data : DataTaskModel)

    fun changeStatus ( id : String , status : String)
    fun onChange(isSuccess : Boolean, msg : String)
}