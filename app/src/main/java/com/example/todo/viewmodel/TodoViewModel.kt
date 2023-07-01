package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.TodoDatabase
import com.example.todo.data.TodoModel
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    val allTodo : LiveData<List<TodoModel>>

    init {
        val database: TodoDatabase? = TodoDatabase.getInstance(getApplication())
        repository = TodoRepository(database!!)
        allTodo = repository.getAllTasks()
    }

    fun insertTask(todoModel: TodoModel) = viewModelScope.launch(Dispatchers.IO){
        repository.insertTask(todoModel)
    }

    fun updateTask(todoModel: TodoModel) = viewModelScope.launch(Dispatchers.IO){
        repository.updateTask(todoModel)
    }

    fun deleteTask(todoModel: TodoModel) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTask(todoModel)
    }
}
