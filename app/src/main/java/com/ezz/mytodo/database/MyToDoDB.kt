package com.ezz.mytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ezz.mytodo.ui.MyApplication

@Database(entities = [ToDoEntity::class], version = 2)
abstract class MyToDoDB: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile
        private var instance: MyToDoDB? = null
        private val LOCK = Any()

        fun getInstance(context: Context): MyToDoDB {

            if (instance == null){
                instance = createDatabase(context)
            }
            return instance as MyToDoDB
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context,
                MyToDoDB::class.java, "MyToDoDB")
                .fallbackToDestructiveMigration()
                .build()
    }
}


