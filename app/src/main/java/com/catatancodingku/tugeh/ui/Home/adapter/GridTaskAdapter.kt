package com.catatancodingku.tugeh.ui.Home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catatancodingku.tugeh.R
import com.catatancodingku.tugeh.ui.Home.model.DataTaskModel
import com.catatancodingku.tugeh.ui.Home.presenter.OnClickItem
import kotlinx.android.synthetic.main.item_task_layout.view.*

class GridTaskAdapter(
    val data: List<DataTaskModel>,
    val onClickItem: OnClickItem
) : RecyclerView.Adapter<GridTaskAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.tv_item_title
        val desc = itemView.tv_item_desc
        val author = itemView.tv_item_author
        val delete = itemView.delete
        val check = itemView.check
        val uncheck = itemView.uncheck

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = data[position]
        holder.title.text = task.title
        holder.desc.text = task.description
        holder.author.text = task.author

        when(task.status){
            "complete" -> {
                holder.uncheck.visibility = View.INVISIBLE
                holder.check.visibility = View.VISIBLE
            }

            "uncomplete" -> {
                holder.check.visibility = View.INVISIBLE
                holder.uncheck.visibility = View.VISIBLE
            }
        }

        holder.uncheck.setOnClickListener {
            holder.uncheck.visibility = View.INVISIBLE
            holder.check.visibility = View.VISIBLE
            onClickItem.changeStatus(task.id ?:"0","complete")
        }

        holder.check.setOnClickListener {
            holder.check.visibility = View.INVISIBLE
            holder.uncheck.visibility = View.VISIBLE
            onClickItem.changeStatus(task.id ?:"0","uncomplete")
        }

        holder.delete.setOnClickListener {
            onClickItem.itemClickDelete(data[holder.adapterPosition].id)
        }

        holder.itemView.setOnClickListener {
            onClickItem.itemClick(task)
        }
    }

    override fun getItemCount(): Int = data.size

}