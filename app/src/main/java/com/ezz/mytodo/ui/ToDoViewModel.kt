package com.ezz.mytodo.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ezz.mytodo.database.ToDoEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ToDoViewModel(app : Application) : AndroidViewModel(app) {
    val todoListLD: MutableLiveData<MutableList<ToDoEntity>> = MutableLiveData()
    private val db = (app as MyApplication).getDB()

    fun getItems(){
        db.todoDao().getAll()

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<MutableList<ToDoEntity>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError: ${e.message}")
                }

                override fun onNext(t: MutableList<ToDoEntity>) {
                    todoListLD.value = t
                }

                override fun onComplete() {
                    //TODO("Not yet implemented")
                }

            })
    }


}