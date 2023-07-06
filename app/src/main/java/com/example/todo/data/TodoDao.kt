package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(todoModel: TodoModel)

    @Update
    fun updateTodo(todoModel: TodoModel)

    @Delete
    fun deleteTodo(todoModel: TodoModel)

    @Query("SELECT * FROM todo_task")
    fun loadTodo(): LiveData<List<TodoModel>>
}
