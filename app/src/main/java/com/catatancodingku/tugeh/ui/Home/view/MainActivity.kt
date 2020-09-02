package com.catatancodingku.tugeh.ui.Home.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.helper.SessionManager
import com.catatancodingku.tugeh.ui.Add.view.AddTaskAct
import com.catatancodingku.tugeh.ui.Home.adapter.GridTaskAdapter
import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel
import com.catatancodingku.tugeh.ui.Home.presenter.OnClickItem
import com.catatancodingku.tugeh.ui.Home.presenter.TaskPresenter
import com.catatancodingku.tugeh.ui.Home.presenter.TaskView
import com.catatancodingku.tugeh.ui.Login.View.LoginAct
import com.catatancodingku.tugeh.ui.edit.view.EditActivity
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "RESPONSE"
    lateinit var taskPresenter: TaskPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sessionManager = SessionManager(this)
        tv_username_main.text   = sessionManager.fullname

        taskPresenter = TaskPresenter(object : TaskView {
            override fun onSuccess(
                data: List<DataTaskModel>?,
                completeTask: Int,
                uncompleteTask: Int
            ) {
                Log.d(TAG, "onSuccess: " + data)

                tv_uncompleted.text = uncompleteTask.toString()
                tv_complete.text = completeTask.toString()
                showGridTask(data)
            }

            override fun onFailed(msg: String) {
                Log.e(TAG, "onFailed: Response = $msg")
            }
        })

        taskPresenter.getDataApi()

        fab_add.setOnClickListener {
            startActivity(Intent(this, AddTaskAct::class.java))
        }

        btn_logout.setOnClickListener {

            sessionManager.editor?.clear()
            sessionManager.editor?.commit()
            startActivity(Intent(this,LoginAct::class.java))
            finish()
        }



    }

    private fun showGridTask(data: List<DataTaskModel>?) {

        val onClickItem = object : OnClickItem {
            override fun itemClickDelete(id: String?) {

                deleteItem(id)
            }

            override fun onDelete(isSuccess: Boolean, msg: String) {

                if (isSuccess) {
                    Toast.makeText(this@MainActivity, "Data sudah terhapus", Toast.LENGTH_SHORT)
                        .show()
                    taskPresenter.getDataApi()
                } else {
                    Toast.makeText(applicationContext, "Gagal menghapus data", Toast.LENGTH_SHORT)
                        .show()

                    Log.e(TAG, "onDelete: $msg")
                }

            }

            // when item click to edit
            override fun itemClick(data: DataTaskModel) {
                val intent = Intent(this@MainActivity,EditActivity::class.java)
                intent.putExtra("TASK_PARCEL",data)
                startActivity(intent)
            }

            //when user change status
            override fun changeStatus(id: String, status: String) {
                taskPresenter.updateStatus(id, status)
            }

            override fun onChange(isSuccess: Boolean, msg: String) {
                if (isSuccess) {
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT)
                        .show()
                    taskPresenter.getDataApi()
                } else {
                    Toast.makeText(applicationContext, "Gagal update data", Toast.LENGTH_SHORT)
                        .show()

                    Log.e(TAG, "onDelete: $msg")
                }
            }
        }


    taskPresenter.setOnClickItem(onClickItem)
        val adapterGrid = GridTaskAdapter(data!!, onClickItem)
        rv_main.adapter = adapterGrid
        rv_main.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun deleteItem(id : String?) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Kamu yakin hapus data?")
        alert.setMessage("Data yang sudah dihapus tidak dapat dikembalikan")
        alert.setPositiveButton("Ya, Hapus",DialogInterface.OnClickListener{w,i ->
            taskPresenter.deleteItemTask(id)
        })
        alert.setNeutralButton("Batal",DialogInterface.OnClickListener{w,i ->})
        alert.show()
    }

    override fun onResume() {
        super.onResume()
        taskPresenter.getDataApi()
    }
}