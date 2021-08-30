package com.ezz.mytodo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer


@Dao
interface ToDoDao {
    @Query("SELECT * FROM todos ORDER BY importance")
    fun getAll(): Observable<MutableList<ToDoEntity>>

    @Insert
    fun insert(todo: ToDoEntity): Completable

    @Delete
    fun delete(todo: ToDoEntity): Completable


}