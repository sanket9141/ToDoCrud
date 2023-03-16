package com.example.todocrud.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todocrud.R
import com.example.todocrud.database.ToDoEntity

class ToDoAdapter(val context: Context, val taskDeleteInterface:DeleteInterface, val taskClickInterface:ClickInterface ):RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private val allTasks = ArrayList<ToDoEntity>()

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val taskName = itemView.findViewById<TextView>(R.id.txt_item_no)
        val date =itemView.findViewById<TextView>(R.id.txt_date)
       // val desc = itemView.findViewById<TextView>(R.id.txtDesc)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = allTasks[position]
        holder.taskName.setText(data.task)
        holder.date.setText("Date: " + data.date)

       // holder.desc.setText(data.description)

        holder.deleteButton.setOnClickListener{
            taskDeleteInterface.onDeleteIconClick(allTasks.get(position))
        }
        holder.itemView.setOnClickListener{
            taskClickInterface.onNoteClick(allTasks.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    fun updateTask(newTask:List<ToDoEntity>){
        allTasks.clear()

        allTasks.addAll(newTask)

        notifyDataSetChanged()
    }
}

interface DeleteInterface {
    // creating a method for click
    // action on delete image view.
    fun onDeleteIconClick(note: ToDoEntity)
}

interface ClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onNoteClick(note: ToDoEntity)
}