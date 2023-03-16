package com.example.todocrud.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todocrud.R
import com.example.todocrud.database.ToDoEntity
import com.example.todocrud.databinding.ActivityMainBinding
import com.example.todocrud.viewModel.TodoViewModel

class MainActivity : AppCompatActivity(), ClickInterface, DeleteInterface {

    lateinit var viewModel : TodoViewModel
    lateinit var taskRV : RecyclerView

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskRV = findViewById(R.id.recyclerView)

        taskRV.layoutManager = LinearLayoutManager(this)

        val taskRVAdapter = ToDoAdapter(this,this,this)

        taskRV.adapter = taskRVAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModel::class.java)

        viewModel.allTasks.observe(this, Observer {
            list -> list?.let{
                taskRVAdapter.updateTask(it)
        }
        })
        binding.newbutton.setOnClickListener{
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: ToDoEntity) {
        viewModel.deleteTask(note)
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: ToDoEntity) {
        val intent = Intent(this@MainActivity,AddTaskActivity::class.java)
        intent.putExtra("taskType","Edit")
        intent.putExtra("taskTitle",note.task)
        intent.putExtra("taskDate", note.date)
        intent.putExtra("taskId",note.id)
        startActivity(intent)
        finish()
    }
}