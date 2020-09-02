package com.catatancodingku.tugeh.ui.Add.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.helper.SessionManager
import com.catatancodingku.tugeh.ui.Add.presenter.AddTaskPresenter
import com.catatancodingku.tugeh.ui.Add.presenter.AddTaskView
import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskAct : AppCompatActivity(), AddTaskView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val sessionManager = SessionManager(this)
        val addTaskPresenter = AddTaskPresenter(this)


        btn_back.setOnClickListener {
            finish()
        }

        btn_save.setOnClickListener {
            var isValid = true
            val titleTask = title_addTask.text.toString()
            val descTask    = detail_task.editText!!.text.toString()

            if (titleTask.isEmpty() || descTask.isEmpty()){
                Toast.makeText(this, "Harap melengkapi isian", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            //Menambahkan data
            if (isValid){
                val author = sessionManager.author
                addTaskPresenter.addTask(titleTask,descTask,author!!)
            }
        }
    }

    override fun onSuccessAddTask(isSuccess: Boolean, msg: String) {
        if (isSuccess){
            Toast.makeText(this, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailedAddTask(msg: String) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        Log.e("RESPONSE", "onFailedAddTask: $msg" )
    }
}