package com.catatancodingku.tugeh.ui.edit.view

import android.content.DialogInterface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel
import com.catatancodingku.tugeh.ui.edit.presenter.EditPresenter
import kotlinx.android.synthetic.main.activity_add_task.*

class EditActivity : AppCompatActivity(), EditView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val editPresenter = EditPresenter(this)
        val data = intent.getParcelableExtra<DataTaskModel>("TASK_PARCEL")
        val id = data.id ?: ""
        val author = data.author ?: ""
        val title = data.title ?: ""
        val description = data.description ?: ""
        val status = data.status ?: "uncomplete"

        title_addTask.setText(title)
        detail_task.editText!!.setText(description)
        btn_delete.visibility = View.VISIBLE

        btn_back.setOnClickListener {
            finish()
        }

        //when user save edited dataset
        btn_save.setOnClickListener {
            val newTitle = title_addTask.text.toString()
            val newDesc = detail_task.editText!!.text.toString()
            if (newTitle.isEmpty() || newDesc.isEmpty()) {
                Toast.makeText(this, "Harap membuat tugas", Toast.LENGTH_SHORT).show()
            } else {
                editPresenter.editItemTask(id, newTitle, newDesc, author, status)
            }
        }

        btn_delete.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Kamu yakin hapus data?")
            alert.setMessage("Data yang sudah dihapus tidak dapat dikembalikan")
            alert.setPositiveButton("Ya, Hapus", DialogInterface.OnClickListener { w, i ->

                editPresenter.deleteItemTask(id)

            })
            alert.setNeutralButton("Batal", DialogInterface.OnClickListener { w, i -> })
            alert.show()
        }

    }

    override fun onSuccessUpdateItem(isSuccess: Boolean, msg: String) {

        if (isSuccess) {
            Toast.makeText(this, "Data sudah diubah", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Gagal mengubah data", Toast.LENGTH_SHORT).show()
            Log.e("RESPONSE", "onSuccessUpdateItem: $msg")
        }

    }

    override fun onFailedtoUpdate(msg: String) {

        Toast.makeText(this, "error : $msg", Toast.LENGTH_SHORT).show()
        Log.e("RESPONSE", "onFailedtoUpdate: $msg")

    }

    override fun onSuccessDeleteItem(isSuccess: Boolean, msg: String) {
        if (isSuccess) {
            Toast.makeText(this, "Data sudah terhapus", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Gagal mengahapus data", Toast.LENGTH_SHORT).show()
            Log.e("RESPONSE", "onSuccessUpdateItem: $msg")
        }
    }

    override fun onFailedtoDeleteItem(msg: String) {
        Toast.makeText(this, "error : $msg", Toast.LENGTH_SHORT).show()
        Log.e("RESPONSE", "onFailedtoUpdate: $msg")
    }
}