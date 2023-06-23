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
import com.example.todo.data.TodoData

class ListAdapter (
    private var context: Context,
    private var dataList: List<TodoData>
): RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, parent, false)
        return MyViewHolder(view)
    }

    fun setData(dataList: List<TodoData>){
        this.dataList = dataList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data: TodoData = dataList[position]
        val titleText = data.title
        val status = data.status

        holder.title.text = titleText

        if(status==1){
            holder.check.visibility = View.VISIBLE
            holder.title.setTextColor(ContextCompat.getColor(context, R.color.grey))
        }

        holder.checkSample.setOnClickListener {
            // mark task as done

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
