package com.catatancodingku.tugeh.helper

import android.content.Context
import android.content.SharedPreferences

class SessionManager(
    val context : Context
) {

    var preference : SharedPreferences? = null
    var editor  : SharedPreferences.Editor? = null

    init {

        preference = context.getSharedPreferences("USER_SESSION",0)
        editor = preference?.edit()
    }

    var login : Boolean?
        get() = preference?.getBoolean("IS_LOGIN",false)

        set(login) {
            editor?.putBoolean("IS_LOGIN",login?:false)
            editor?.commit()
        }

    var author : String?
        get() = preference?.getString("AUTHOR","")
        set(value) {
            editor?.putString("AUTHOR",value)
        }

    var fullname : String?
    get() = preference?.getString("FIRST_NAME","")
    set(value) {
        editor?.putString("FIRST_NAME",value)
    }

    var lastName : String?
    get() = preference?.getString("LAST_NAME","")
    set(value) {
        editor?.putString("LAST_NAME",value)
    }



}