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
    private val toDoAdapter = ToDoAdapter(toDoList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}