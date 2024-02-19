package com.dhanshree.selftrack

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dhanshree.selftrack.Models.TaskList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAddTaskToDb: Button = findViewById(R.id.btnAdd)
        btnAddTaskToDb.setOnClickListener {
            addTaskToDb()
        }
    }

    fun addTaskToDb() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Task")
        val customLayout: View = layoutInflater.inflate(R.layout.add_task_pop_up, null)
        builder.setView(customLayout)
        builder.setPositiveButton("Add") { dialog: DialogInterface?, which ->
            Int
            val txtTaskName = customLayout.findViewById<EditText>(R.id.popTaskName)
            val txtTaskDesc = customLayout.findViewById<EditText>(R.id.popTaskDesc)
            val taskList = TaskList(txtTaskName.text.toString(), txtTaskDesc.text.toString())


            storeDataIntoLocal(txtTaskName.text.toString(), txtTaskDesc.text.toString())
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun storeDataIntoLocal(task_name: String, task_desc: String) {
        Toast.makeText(this, task_name, Toast.LENGTH_SHORT).show()
        val sharedPreferences = getSharedPreferences("taskDB", MODE_PRIVATE)
        val addTaskList = sharedPreferences.edit()
        addTaskList.putString("TaskName", task_name)
        addTaskList.putString("TaskDescription", task_desc)
        addTaskList.apply()
    }


}