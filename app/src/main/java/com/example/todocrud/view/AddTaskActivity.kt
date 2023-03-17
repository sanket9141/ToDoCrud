package com.example.todocrud.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todocrud.database.ToDoEntity
import com.example.todocrud.databinding.ActivtyAddTaskBinding
import com.example.todocrud.viewModel.TodoViewModel
import java.util.*

class AddTaskActivity:AppCompatActivity() {

    lateinit var binding: ActivtyAddTaskBinding
    lateinit var viewmodel: TodoViewModel
    var taskkId = -1


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtyAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TodoViewModel::class.java)


        binding.editDate?.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datepicker =
                DatePickerDialog(this, { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    binding.editDate?.setText("$mDay/${mMonth + 1}/$mYear")
                }, year, month, day)
            datepicker.show()
        }

        val taskType = intent.getStringExtra("taskType")
        if (taskType.equals("Edit")) {
            val taskTitle = intent.getStringExtra("taskTitle")
            val updateDate = intent.getStringExtra("taskDate")
            taskkId = intent.getIntExtra("taskId", -1)

            binding.addbutton.setText("Update Task")
            binding.taskEditText.setText(taskTitle)
            binding.editDate.setText(updateDate)
           // binding.addDescription.setText(updatedesc)
        }


        binding.addbutton.setOnClickListener {
            val taskTitle = binding.taskEditText.text.toString()
            val taskDate = binding.editDate.text.toString()
            //val taskDesc = binding.addDescription.text.toString()

            if (taskType.equals("Edit")) {
                if (taskTitle.isNotEmpty() && taskDate.isNotEmpty()) {

                    val updatedTask = ToDoEntity(taskTitle, taskDate)

                    updatedTask.id = taskkId

                    viewmodel.updateTask(updatedTask)
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                    Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (taskTitle.isNotEmpty() && taskDate.isNotEmpty()) {

                    viewmodel.addTask(ToDoEntity(taskTitle, taskDate))

                    startActivity(Intent(applicationContext, MainActivity::class.java))

                    Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(this,"Fields cannot be Empty.!!!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
