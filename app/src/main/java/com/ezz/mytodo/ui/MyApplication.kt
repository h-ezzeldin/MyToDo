package com.ezz.mytodo.ui

import android.app.Application
import com.ezz.mytodo.database.MyToDoDB

class MyApplication: Application() {
    lateinit var db: MyToDoDB

    override fun onCreate() {
        super.onCreate()
        db = MyToDoDB.getInstance(applicationContext)
    }

    fun getDB(): MyToDoDB{
        return db
    }
}