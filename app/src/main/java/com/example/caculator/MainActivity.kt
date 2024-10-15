package com.example.caculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textResult: TextView
    lateinit var textInput: TextView
    var op = ""
    var op1: Int = 0
    var op2: Int = 0
    var currentInput = ""
    var isNewOp = true
    var input = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInput = findViewById(R.id.input)
        textResult = findViewById(R.id.result)

        arrayOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div,
            R.id.btn_c, R.id.btn_equal, R.id.btn_dot, R.id.btn_change, R.id.btn_bs, R.id.btn_ce
        ).forEach { findViewById<Button>(it).setOnClickListener(this) }
    }
    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9 -> {
                val button = p0 as Button
                handleNumber(button.text.toString())}

                R.id.btn_add -> handleOp("+")
                R.id.btn_sub -> handleOp("-")
                R.id.btn_mul -> handleOp("x")
                R.id.btn_div -> handleOp("/")
                R.id.btn_c -> clearAll()
                R.id.btn_equal -> handleEqual()
                R.id.btn_bs -> backSpace()
                R.id.btn_ce -> clearEntry()
                R.id.btn_change -> changeSign()
            }
        }

    fun handleNumber(number: String) {
        if (isNewOp) {
            currentInput = number
            input += number
            isNewOp = false
        } else {
            currentInput += number
            input += number
        }
        textResult.text = currentInput
        textInput.text = input
    }

        fun handleOp(op: String) {
            if (currentInput.isNotEmpty()) {
                op1 = currentInput.toInt()
                isNewOp = true
                this.op = op
                input += " " + op + " "
                textInput.text = input
                currentInput = ""
            }
        }

    fun handleEqual() {
        if (currentInput.isNotEmpty()) {
            op2 = currentInput.toInt()
            val result = when (op){
                "+" -> op1 + op2
                "-" -> op1 - op2
                "x" -> op1 * op2
                "/" -> if (op2 != 0) op1 / op2  else "Error"
                else -> "Error"
            }
            input += "="
            textResult.text = result.toString()
            textInput.text = input.toString()
            currentInput = result.toString()
            input = ""
            isNewOp = true
        }
    }

    fun clearAll() {
        op1 = 0
        op2 = 0
        this.op = ""
        currentInput = ""
        input = ""
        textInput.text = ""
        textResult.text = "0"
    }

    fun backSpace() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
            input = input.substring(0, input.length - 1)
            textInput.text = input
            textResult.text = currentInput
        }
    }

    fun clearEntry(){
        input = input.substring(0, input.length - currentInput.length)
        textInput.text = input
        currentInput = ""
        textResult.text = "0"
    }

    fun changeSign(){
        if (currentInput.isNotEmpty()){
            currentInput = (-currentInput.toInt()).toString()
            textResult.text = currentInput
        }
    }

}