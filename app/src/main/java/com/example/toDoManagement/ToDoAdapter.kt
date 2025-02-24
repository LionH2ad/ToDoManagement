package com.example.toDoManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toDoManagement.databinding.ItemToDoBinding

class ToDoAdapter(private val toDoList: MutableList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(private val binding: ItemToDoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDo: ToDo) {
            binding.apply {
                tvToDo.text = toDo.detail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemToDoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}