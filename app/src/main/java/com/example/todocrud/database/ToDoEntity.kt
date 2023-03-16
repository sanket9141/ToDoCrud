package com.example.todocrud.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ToDoTable")
class ToDoEntity(

                @ColumnInfo(name = "Title")var task:String,
               //  @ColumnInfo(name = "Description") var description:String,
                 @ColumnInfo(name = "Date")var date:String){
    @PrimaryKey(autoGenerate = true)
    var id =0
}


