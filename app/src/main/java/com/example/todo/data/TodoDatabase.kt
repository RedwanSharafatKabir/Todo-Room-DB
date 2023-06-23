package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        val LOCK = Any()
        const val DATABASE_NAME = "hishabee_database"
        var dbInstance: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase? {
            if (dbInstance == null) {
                synchronized(LOCK) {
                    dbInstance = databaseBuilder(context.applicationContext, TodoDatabase::class.java, DATABASE_NAME).build()
                }
            }

            return dbInstance
        }
    }
}
