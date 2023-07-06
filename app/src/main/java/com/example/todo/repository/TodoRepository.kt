package com.example.todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo.data.TodoDao
import com.example.todo.data.TodoDatabase
import com.example.todo.data.TodoModel

class TodoRepository(application: Application) {

    var database: TodoDatabase? = null
    var todoDao: TodoDao? = null
    private var taskList: LiveData<List<TodoModel>>? = null

    init {
        this.database = TodoDatabase.getInstance(application)
        todoDao = database!!.todoDao()
        taskList = todoDao!!.loadTodo()
    }

    fun insertTask(todoModel: TodoModel) {
        TodoDatabase.databaseWriteExecutor.execute { todoDao!!.insertTodo(todoModel) }
    }

    fun deleteTask(todoModel: TodoModel) {
        TodoDatabase.databaseWriteExecutor.execute { todoDao!!.deleteTodo(todoModel) }
    }

    fun updateTask(todoModel: TodoModel) {
        TodoDatabase.databaseWriteExecutor.execute { todoDao!!.updateTodo(todoModel) }
    }

    fun getAllTasks(): LiveData<List<TodoModel>> {
        return taskList!!
    }
}
