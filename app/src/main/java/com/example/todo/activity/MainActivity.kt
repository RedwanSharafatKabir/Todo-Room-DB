package com.example.todo.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adapter.ListAdapter
import com.example.todo.data.TodoModel
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.CustomDialogAddTaskBinding
import com.example.todo.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: TodoViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private var listAdapter: ListAdapter? = null
    private lateinit var taskList: MutableList<TodoModel>
    private var recyclerViewState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        } catch (e: Exception){
            e.printStackTrace()
        }

        taskList = arrayListOf()
        recyclerView = binding.taskList
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        recyclerView.setHasFixedSize(false)
        listAdapter = ListAdapter(this, taskList, viewModel)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerViewState = recyclerView.layoutManager!!.onSaveInstanceState()!!
            }
        })

        loadData()

        binding.addNewTask.setOnClickListener(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadData() {
        viewModel.tasks.observe(this) { taskEntries ->
            taskList = taskEntries.toMutableList()

            listAdapter!!.setData(taskList)
            recyclerView.adapter = listAdapter
            listAdapter!!.notifyDataSetChanged()
            recyclerView.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val tasks: List<TodoModel> = listAdapter!!.getData()
                viewModel.deleteTask(tasks[position])
            }

        }).attachToRecyclerView(binding.taskList)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.addNewTask -> {
                val dialog = Dialog(this)
                val dialogBinding = CustomDialogAddTaskBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                val width = ViewGroup.LayoutParams.MATCH_PARENT
                val height = ViewGroup.LayoutParams.WRAP_CONTENT
                dialog.window!!.setLayout(width, height)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                dialog.setCancelable(false)

                dialogBinding.closeDialog.setOnClickListener {
                    dialog.dismiss()
                }

                dialogBinding.addNewTask.setOnClickListener {
                    val title = dialogBinding.enterTaskTitle.text.toString()

                    if(title.isNotEmpty()){
                        dialogBinding.progressBar.visibility = View.VISIBLE

                        val id = taskList.size + 1
                        val data = TodoModel(id, title, 0)
                        viewModel.insertTask(data)

                        Toast.makeText(this@MainActivity, "Task assigned successfully", Toast.LENGTH_LONG).show()
                        dialog.dismiss()

                    } else {
                        dialogBinding.enterTaskTitle.error = "Please assign your task"
                    }
                }

                dialog.show()
            }
        }
    }
}
