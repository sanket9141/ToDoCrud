package com.example.todocrud.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todocrud.database.ToDoDatabase
import com.example.todocrud.database.ToDoEntity
import com.example.todocrud.repository.TodoRepo
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    val allTasks:LiveData<List<ToDoEntity>>
    val repository:TodoRepo

    init {
        val dao = ToDoDatabase.getInstance(application).TodoDAO()
        repository = TodoRepo(dao)
        allTasks = repository.allTodo
    }
    fun deleteTask(note:ToDoEntity) = viewModelScope.launch {
        repository.deleteData(note)
    }
    fun updateTask(note: ToDoEntity) = viewModelScope.launch{
        repository.updateData(note)
    }

    fun addTask(note: ToDoEntity) = viewModelScope.launch {
        repository.insertData(note)
    }
}