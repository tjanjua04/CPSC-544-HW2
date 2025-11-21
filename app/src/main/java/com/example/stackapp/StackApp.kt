package com.example.stackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StackApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stack_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quitButton = findViewById<Button>(R.id.button8)

        val pushButton = findViewById<Button>(R.id.button9)

        val popButton = findViewById<Button>(R.id.button10)

        quitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val stack = ArrayDeque<Int>()
        val logField = findViewById<TextView>(R.id.textView8)

        fun push(intInput: Int) {
            if (stack.size == 3) {
                logField.text = "Stack is full"
                return
            }
            if (intInput !in 0..9) {
                logField.text = "Input is not within range of 0-9."
                return
            }
            stack.addLast(intInput)
        }

        fun syncStackField() {
            var stackPrettyText = ""
            stack.asReversed().forEach { stackVal ->
                stackPrettyText += stackVal.toString() + "\n"
            }
            val stackField = findViewById<TextView>(R.id.textView9)
            stackField.text = stackPrettyText
        }

        pushButton.setOnClickListener {
            val inputField = findViewById<EditText>(R.id.editTextNumber3)
            val inputInt = inputField.text.toString()
            val intInput = inputInt.toInt()
            push(intInput)
            syncStackField()
        }


        fun pop() {
            if (stack.isEmpty()) {
                logField.text = "Stack is empty. Cannot pop."
                return
            }
            val poppedElement = stack.removeLast()
            logField.text = "$poppedElement was popped."
        }
        popButton.setOnClickListener {
            pop()
            syncStackField()
        }
    }
}