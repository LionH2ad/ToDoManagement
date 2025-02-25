package com.example.toDoManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.toDoManagement.databinding.ItemToDoBinding

class ToDoAdapter(private val toDoList: MutableList<ToDo>, private val onItemDeleted: (Int) -> Unit) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(private val binding: ItemToDoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDo: ToDo, position: Int) {
            binding.apply {
                tvToDoNumber.text = "${position + 1}." // 순번 자동 추가
                tvToDo.text = toDo.detail
                // 삭제 버튼 클릭 시
                btnToDoDelete.setOnClickListener{
                    showDeleteDialog(position, toDo.detail)
                }

                root.setOnLongClickListener {
                    showDeleteDialog(position, toDo.detail)
                    true
                }
            }
        }

        private fun showDeleteDialog(position: Int, detail: String){
            AlertDialog.Builder(binding.root.context).apply{
                setTitle("삭제 확인")
                setMessage("\"${detail}\" 할 일을 삭제하시겠습니까?")
                setPositiveButton("삭제") { _, _ ->
                    onItemDeleted(position)
                }
                setNegativeButton("취소", null)
            }.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemToDoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDoList[position], position)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}