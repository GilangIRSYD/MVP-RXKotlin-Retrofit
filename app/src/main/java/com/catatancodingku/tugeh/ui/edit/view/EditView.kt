package com.catatancodingku.tugeh.ui.edit.view

interface EditView {
    fun onSuccessUpdateItem(isSuccess : Boolean, msg : String)
    fun onFailedtoUpdate(msg: String)

    fun onSuccessDeleteItem(isSuccess : Boolean, msg : String)
    fun onFailedtoDeleteItem(msg: String)
}