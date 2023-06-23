package com.example.todo.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.CustomDialogAddTaskBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        binding.addNewTask.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.addNewTask -> {
                val dialog = Dialog(this)
                val dialogBinding = CustomDialogAddTaskBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                val width = ViewGroup.LayoutParams.MATCH_PARENT
                val height = ViewGroup.LayoutParams.WRAP_CONTENT
                dialog.window!!.setLayout(width, height)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                dialog.setCancelable(false)

                dialogBinding.closeDialog.setOnClickListener {
                    dialog.dismiss()
                }

                dialogBinding.addNewTask.setOnClickListener {
                    val title = dialogBinding.enterTaskTitle.text.toString()

                    if(title.isNotEmpty()){
                        dialogBinding.progressBar.visibility = View.VISIBLE

                        // add to room db

                        dialog.dismiss()

                    } else {
                        dialogBinding.enterTaskTitle.error = "Please assign your task"
                    }
                }

                dialog.show()
            }
        }
    }

    private fun loadData() {

    }
}
