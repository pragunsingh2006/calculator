package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.tvResult)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnPlus, R.id.btnMinus,
            R.id.btnMultiply, R.id.btnDivide,
            R.id.btnDot
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                expression += (it as Button).text.toString()
                result.text = expression
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            result.text = "0"
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val res = evaluate(expression)
                result.text = res.toString()
                expression = res.toString()
            } catch (e: Exception) {
                result.text = "Error"
                expression = ""
            }
        }
    }

    private fun evaluate(exp: String): Double {
        val tokens = exp.split("(?<=[+\\-*/])|(?=[+\\-*/])".toRegex())
        var result = tokens[0].toDouble()
        var i = 1

        while (i < tokens.size) {
            val op = tokens[i]
            val num = tokens[i + 1].toDouble()

            when (op) {
                "+" -> result += num
                "-" -> result -= num
                "*" -> result *= num
                "/" -> result /= num
            }
            i += 2
        }

        return result
    }
}