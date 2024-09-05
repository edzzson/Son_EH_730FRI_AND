package com.scholarlink.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var numberDisplay: TextView
    private var input = StringBuilder()
    private var firstNumber: Double? = null
    private var currentOperation: String? = null
    private var hasDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        numberDisplay = findViewById(R.id.number_display)

        // Number buttons
        val buttons = listOf(
            Pair(R.id.btn_0, "0"), Pair(R.id.btn_1, "1"), Pair(R.id.btn_2, "2"),
            Pair(R.id.btn_3, "3"), Pair(R.id.btn_4, "4"), Pair(R.id.btn_5, "5"),
            Pair(R.id.btn_6, "6"), Pair(R.id.btn_7, "7"), Pair(R.id.btn_8, "8"),
            Pair(R.id.btn_9, "9")
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                onNumberClick(value)
            }
        }

        findViewById<Button>(R.id.btn_decimal).setOnClickListener {
            onDecimalClick()
        }

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            onBackspaceClick()
        }

        findViewById<Button>(R.id.btn_plus).setOnClickListener {
            onOperationClick("+")
        }

        findViewById<Button>(R.id.btn_minus).setOnClickListener {
            onOperationClick("-")
        }

        findViewById<Button>(R.id.btn_equal).setOnClickListener {
            onEqualClick()
        }

        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            onClearClick()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onNumberClick(value: String) {
        val inputString = input.toString()

        // Limit to 2 decimal places
        if (hasDecimal && inputString.substringAfter('.').length >= 2) return

        // Limit to 2 numbers before the decimal point
        if (!hasDecimal && inputString.length >= 2) return

        input.append(value)
        updateDisplay()
    }

    private fun onDecimalClick() {
        if (!hasDecimal && input.isNotEmpty()) {
            input.append('.')
            hasDecimal = true
            updateDisplay()
        }
    }

    private fun onBackspaceClick() {
        if (input.isNotEmpty()) {
            if (input.last() == '.') {
                hasDecimal = false
            }
            input.deleteCharAt(input.length - 1)
            updateDisplay()
        }
    }

    private fun onOperationClick(operation: String) {
        if (input.isNotEmpty()) {
            firstNumber = input.toString().toDouble()
            currentOperation = operation
            input.clear()
            hasDecimal = false
        }
    }

    private fun onEqualClick() {
        if (firstNumber != null && input.isNotEmpty()) {
            val secondNumber = input.toString().toDouble()
            val result = when (currentOperation) {
                "+" -> firstNumber!! + secondNumber
                "-" -> firstNumber!! - secondNumber
                else -> return
            }

            firstNumber = result
            input.clear()
            input.append(formatResult(result))
            hasDecimal = input.contains(".")
            updateDisplay()
        }
    }

    private fun onClearClick() {
        input.clear()
        firstNumber = null
        currentOperation = null
        hasDecimal = false
        updateDisplay()
    }

    private fun updateDisplay() {
        numberDisplay.text = if (input.isEmpty()) "0" else input.toString()
    }

    private fun formatResult(result: Double): String {
        return if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            String.format("%.2f", result)
        }
    }
}
