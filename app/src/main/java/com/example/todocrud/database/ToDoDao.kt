package com.example.todocrud.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(note:ToDoEntity)

    @Delete
    suspend fun deleteData(note: ToDoEntity)

   @Query("SELECT * FROM ToDoTable")
    fun getAllData():LiveData<List<ToDoEntity>>

    @Update
    suspend fun updateData(note: ToDoEntity)

}