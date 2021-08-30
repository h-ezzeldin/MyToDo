package com.ezz.mytodo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class ToDoEntity(val title: String,
                 val body: String,
                 val importance: Int) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null


}




