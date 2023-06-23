package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo.data.TodoDatabase
import com.example.todo.data.TodoModel
import com.example.todo.repository.TodoRepository

class TodoViewModel(application: Application?) : AndroidViewModel(application!!) {

    val tasks: LiveData<List<TodoModel>> = TODO()
    private val todoRepository: TodoRepository = TODO()

    init {
        val database: TodoDatabase? = TodoDatabase.getInstance(getApplication())
        todoRepository = TodoRepository(database!!)
        tasks = todoRepository.getAllTasks()
    }

    fun getTasks(): LiveData<List<TodoModel>> {
        return tasks
    }

    fun insertTask(todoModel: TodoModel) {
        todoRepository.insertTask(todoModel)
    }

    fun updateTask(todoModel: TodoModel) {
        todoRepository.updateTask(todoModel)
    }

    fun deleteTask(todoModel: TodoModel) {
        todoRepository.deleteTask(todoModel)
    }
}
