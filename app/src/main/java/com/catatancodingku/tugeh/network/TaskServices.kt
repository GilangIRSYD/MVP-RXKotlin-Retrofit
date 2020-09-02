package com.catatancodingku.tugeh.network

import com.catatancodingku.tugeh.ui.Add.model.AddTaskActionResponse
import com.catatancodingku.tugeh.ui.Home.model.TaskModel
import com.catatancodingku.tugeh.ui.Home.model.TaskModelResponse
import com.catatancodingku.tugeh.ui.Login.Model.UserLoginResponse
import com.catatancodingku.tugeh.ui.Register.model.RegisterResponse
import com.catatancodingku.tugeh.ui.edit.model.EditTaskActionResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskServices {

    @GET("getTask.php")
    fun getTask(): Flowable<TaskModelResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun getUserLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<UserLoginResponse>

    @FormUrlEncoded
    @POST("register.php")
    fun createAccount(
        @Field("first_name") firstName : String,
        @Field("last_name") lastName : String,
        @Field("email") email : String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("insert.php")
    fun addTask(
        @Field("title") title : String,
        @Field("desc") desc : String,
        @Field("author") author : String,
        @Field("status") status : String
    ) : Call<AddTaskActionResponse>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteItemTask(
        @Field("id") id : String?
    ) : Flowable<TaskModel>

    @FormUrlEncoded
    @POST("update.php")
    fun updateItemTask(
        @Field("id") id : String,
        @Field("title") title : String,
        @Field("desc") desc : String,
        @Field("author") author : String,
        @Field("status") status : String
    ) : Flowable<EditTaskActionResponse>

    @FormUrlEncoded
    @POST("updateStatus.php")
    fun changeStatusItem(
        @Field("id") id : String?,
        @Field("status") status : String?
    ) : Flowable<TaskModel>
}