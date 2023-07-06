package com.example.todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.TodoDao
import com.example.todo.data.TodoModel
import com.example.todo.viewmodel.TodoViewModel

class ListAdapter (
    var context: Context,
    var dataList: MutableList<TodoModel>,
    var viewModel: TodoViewModel
): RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, parent, false)
        return MyViewHolder(view)
    }

    fun setData(dataList: MutableList<TodoModel>){
        this.dataList = dataList
    }

    fun getData(): MutableList<TodoModel> {
        return dataList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: TodoModel = dataList[position]
        val titleText = data.title
        val status = data.status

        holder.title.text = titleText

        if(status==1){
            holder.check.visibility = View.VISIBLE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey))
        }

        holder.checkSample.setOnClickListener {
            // mark task as done
            val newData = TodoModel(titleText, 1)
            viewModel.updateTask(newData)

            holder.check.visibility = View.VISIBLE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.taskTitle)
        var check: ImageView = itemView.findViewById(R.id.check)
        var checkSample: ImageView = itemView.findViewById(R.id.checkSample)
    }
}
