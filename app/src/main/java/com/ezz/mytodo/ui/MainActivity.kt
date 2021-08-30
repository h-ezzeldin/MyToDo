package com.ezz.mytodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ezz.mytodo.R
import com.ezz.mytodo.adapters.ToDoAdapter
import com.ezz.mytodo.database.MyToDoDB
import com.ezz.mytodo.database.ToDoEntity
import com.ezz.mytodo.databinding.ActivityMainBinding
import com.ezz.mytodo.databinding.DialogNewTodoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var db : MyToDoDB
    private lateinit var toDoViewModel :ToDoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        db = (this.application as MyApplication).getDB()
        b.newTodoFab.setOnClickListener {
            showDialog()
        }
        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        toDoViewModel.getItems()
        val tAdapter = ToDoAdapter()


        b.todoRecycler.adapter = tAdapter

        toDoViewModel.todoListLD.observe(this,{ list -> run {tAdapter.setList(list)}})

    }
    private fun showDialog(){
        val dialogBinding: DialogNewTodoBinding = DialogNewTodoBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogBinding.root)
            .show()

        dialogBinding.submitButton.setOnClickListener {
            var imp = 1
            imp = when (dialogBinding.importanceGroup.checkedChipId) {
                R.id.chip1 -> {
                    1
                }
                R.id.chip2 -> {
                    2
                }
                else -> {
                    3
                }
            }
            var title: String = dialogBinding.titleInput.text.toString()
            var body: String = dialogBinding.bodyInput.text.toString()
            db.todoDao().insert(ToDoEntity(title=title, body = body, importance = imp))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    Log.d("TAG","showDialog: onComplete")
                    //toDoViewModel.getItems()
                    }
            dialog.dismiss()

        }
        dialogBinding.cancelButton.setOnClickListener { dialog.dismiss() }
    }
}