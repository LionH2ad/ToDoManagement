package com.example.toDoManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toDoManagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val toDoList = mutableListOf<ToDo>()
    private lateinit var toDoAdapter : ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toDoAdapter = ToDoAdapter(toDoList) { position ->
            removeToDo(position) // 삭제 기능 연결
        }

        binding.apply {
            rvToDo.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = toDoAdapter
            }
            fabAddToDo.setOnClickListener{
                showAddToDoDialog()
            }
        }
    }

    private fun showAddToDoDialog(){
        val editText = EditText(this)
        AlertDialog.Builder(this).apply {
            setTitle("새 할 일 추가")
            setView(editText)
            setPositiveButton("추가") { _,_ ->
                val toDoDetail = editText.text.toString()
                if(toDoDetail.isNotBlank()){
                    toDoList.add(ToDo(toDoDetail))
                    toDoAdapter.notifyItemInserted(toDoList.size -1)
                }
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun removeToDo(position: Int) {
        toDoList.removeAt(position)
        toDoAdapter.notifyItemRemoved(position)
        toDoAdapter.notifyItemRangeChanged(position, toDoList.size) // 순번 재정렬
    }
}