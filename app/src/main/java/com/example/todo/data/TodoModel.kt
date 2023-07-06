package com.example.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_task")
data class TodoModel (
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "status") var status: Int) {

    @PrimaryKey(autoGenerate = true) var id = 0
}
