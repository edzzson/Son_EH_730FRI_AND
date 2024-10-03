package com.scholarlink.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentResult: Double = 0.0
    private var operator: String? = null
    private var isOperatorPressed: Boolean = false
    private var isNewOperation: Boolean = true
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.number_display)

        // Number buttons
        val btn0: Button = findViewById(R.id.btn_0)
        val btn1: Button = findViewById(R.id.btn_1)
        val btn2: Button = findViewById(R.id.btn_2)
        val btn3: Button = findViewById(R.id.btn_3)
        val btn4: Button = findViewById(R.id.btn_4)
        val btn5: Button = findViewById(R.id.btn_5)
        val btn6: Button = findViewById(R.id.btn_6)
        val btn7: Button = findViewById(R.id.btn_7)
        val btn8: Button = findViewById(R.id.btn_8)
        val btn9: Button = findViewById(R.id.btn_9)

        // Operator buttons
        val btnAdd: Button = findViewById(R.id.additional_button)
        val btnSubtract: Button = findViewById(R.id.minus_button)
        val btnEquals: Button = findViewById(R.id.equals_button)
        val btnClear: Button = findViewById(R.id.clear_button)
        val btnErase: ImageButton = findViewById(R.id.erase_button)
        val btnDecimal: Button = findViewById(R.id.btn_decimal)

        // Number button listeners
        val numberClickListener = { number: String ->
            if (isNewOperation) {
                display.text = ""
                isNewOperation = false
            }
            display.append(number)
        }

        btn0.setOnClickListener { numberClickListener("0") }
        btn1.setOnClickListener { numberClickListener("1") }
        btn2.setOnClickListener { numberClickListener("2") }
        btn3.setOnClickListener { numberClickListener("3") }
        btn4.setOnClickListener { numberClickListener("4") }
        btn5.setOnClickListener { numberClickListener("5") }
        btn6.setOnClickListener { numberClickListener("6") }
        btn7.setOnClickListener { numberClickListener("7") }
        btn8.setOnClickListener { numberClickListener("8") }
        btn9.setOnClickListener { numberClickListener("9") }
        btnDecimal.setOnClickListener { numberClickListener(".") }

        // Operator button listeners
        btnAdd.setOnClickListener { handleOperator("+") }
        btnSubtract.setOnClickListener { handleOperator("-") }

        // Clear button
        btnClear.setOnClickListener {
            clear()
        }

        // Backspace button
        btnErase.setOnClickListener {
            val currentText = display.text.toString()
            if (currentText.isNotEmpty()) {
                display.text = currentText.substring(0, currentText.length - 1)
            }
        }

        // Equals button
        btnEquals.setOnClickListener {
            if (operator != null) {
                val operand2 = display.text.toString().toDoubleOrNull() ?: 0.0
                currentResult = when (operator) {
                    "+" -> currentResult + operand2
                    "-" -> currentResult - operand2
                    else -> currentResult
                }
                display.text = formatResult(currentResult)
                isNewOperation = true
                isOperatorPressed = false
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun handleOperator(op: String) {
        val currentText = display.text.toString()

        if (!isOperatorPressed) {
            // This is the first time an operator is pressed, so store the first operand
            currentResult = currentText.toDoubleOrNull() ?: 0.0
            operator = op
            isOperatorPressed = true
            isNewOperation = true
        } else {
            // If another operator is pressed after a result, calculate the new result
            val operand2 = currentText.toDoubleOrNull() ?: 0.0
            currentResult = when (operator) {
                "+" -> currentResult + operand2
                "-" -> currentResult - operand2
                else -> currentResult
            }
            operator = op
            display.text = formatResult(currentResult)
            isNewOperation = true
        }
    }

    private fun formatResult(result: Double): String {
        return if (result % 1 == 0.0) {
            // Whole number, remove decimal part
            result.toInt().toString()
        } else {
            // Decimal number, format to 2 decimal places
            decimalFormat.format(result)
        }
    }

    private fun clear() {
        display.text = "0"
        currentResult = 0.0
        operator = null
        isOperatorPressed = false
        isNewOperation = true
    }

}
