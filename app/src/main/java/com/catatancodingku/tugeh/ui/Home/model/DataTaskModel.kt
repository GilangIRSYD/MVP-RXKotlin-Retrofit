package com.catatancodingku.tugeh.ui.Home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataTaskModel (

    val id : String? = null,
    val title : String? = null,
    val description : String? = null,
    val author : String? = null,
    val status : String? = null
) : Parcelable