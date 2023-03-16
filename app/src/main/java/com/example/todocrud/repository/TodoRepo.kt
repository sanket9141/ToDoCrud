package com.example.todocrud.repository

import androidx.lifecycle.LiveData
import com.example.todocrud.database.ToDoDao
import com.example.todocrud.database.ToDoEntity

class TodoRepo(private val toDoDaoRepo: ToDoDao) {

    val allTodo:LiveData<List<ToDoEntity>> = toDoDaoRepo.getAllData()

    suspend fun insertData(note:ToDoEntity){
        toDoDaoRepo.insertData(note)
    }

    suspend fun deleteData(note: ToDoEntity){
        toDoDaoRepo.deleteData(note)
    }
    suspend fun updateData(note: ToDoEntity){
        toDoDaoRepo.updateData(note)
    }

}