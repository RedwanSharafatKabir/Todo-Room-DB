package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var favouritesRoomDatabase: TodoDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS
        )

        fun getInstance(context: Context): TodoDatabase? {
            if (favouritesRoomDatabase == null) {
                synchronized(TodoDatabase::class.java) {
                    if (favouritesRoomDatabase == null) {
                        favouritesRoomDatabase = databaseBuilder(
                            context.applicationContext,
                            TodoDatabase::class.java, "hishabee_database"
                        ).build()
                    }
                }
            }
            return favouritesRoomDatabase
        }
    }
}

//
//@Database(entities = [TodoModel::class], version = 1)
//abstract class TodoDatabase : RoomDatabase() {
//
//    abstract fun todoDao(): TodoDao
//
//    companion object {
//        val LOCK = Any()
//        const val DATABASE_NAME = "hishabee_database"
//        var dbInstance: TodoDatabase? = null
//
//        fun getInstance(context: Context): TodoDatabase? {
//            if (dbInstance == null) {
//                synchronized(LOCK) {
//                    dbInstance = databaseBuilder(context.applicationContext, TodoDatabase::class.java, DATABASE_NAME).build()
//                }
//            }
//
//            return dbInstance
//        }
//    }
//}
