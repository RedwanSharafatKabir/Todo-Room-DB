package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.TodoDatabase
import com.example.todo.data.TodoModel

class TodoRepository(database: TodoDatabase) {

    var database: TodoDatabase
    lateinit var taskList: LiveData<List<TodoModel>>

    init {
        this.database = database
    }

    fun insertTask(todoModel: TodoModel){
        database.todoDao().insertTodo(todoModel)
    }

    fun getAllTasks(): LiveData<List<TodoModel>> {
        taskList = database.todoDao().loadTodo()
        return taskList
    }

    fun deleteTask(todoModel: TodoModel) {
        database.todoDao().deleteTodo(todoModel)
    }

    fun updateTask(todoModel: TodoModel) {
        database.todoDao().updateTodo(todoModel)
    }
}
