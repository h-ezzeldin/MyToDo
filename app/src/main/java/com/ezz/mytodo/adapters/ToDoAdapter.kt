package com.ezz.mytodo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ezz.mytodo.R
import com.ezz.mytodo.database.ToDoEntity
import com.ezz.mytodo.databinding.TodoItemLayoutBinding
import java.util.ArrayList

class ToDoAdapter: RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    var dataList: MutableList<ToDoEntity> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.b.title.text = dataList[position].title
        holder.b.importanceChip.text = dataList[position].importance.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<ToDoEntity>){
        this.dataList = list
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var b: TodoItemLayoutBinding
        init {
            b = TodoItemLayoutBinding.bind(itemView)
        }

    }
}