package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var equalsButton: Button
    private lateinit var clearButton: Button

    private var operand: Double = 0.0
    private var operation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()


        clearButton.setOnClickListener {
            clearButton()
        }

        clearButton.setOnLongClickListener {
            clearTextFromResult()
        }


        equalsButton.setOnClickListener {

            val secOperandText: String = resultTextView.text.toString()
            var secOperand: Double = 0.0

            if (secOperandText.isNotEmpty()) {
                secOperand = secOperandText.toDouble()
            }

            when (this.operation) {
                "+" -> resultTextView.text = (operand + secOperand).toString()
                "-" -> resultTextView.text = (operand - secOperand).toString()
                "*" -> resultTextView.text = (operand * secOperand).toString()
                "/" -> resultTextView.text = (operand / secOperand).toString()
                "%" -> resultTextView.text = (operand / 100 * secOperand).toString()
                "√" -> resultTextView.text = (sqrt(operand)).toString()
                "+/-" -> resultTextView.text = (operand * -1).toString()
            }

        }

    }


    fun changeSign(view: View) {
        if (resultTextView.text.isNotEmpty()) {
            operand = resultTextView.text.toString().toDouble()
            operand *= -1
            resultTextView.text = operand.toString()
        }
    }

    private fun init() {
        resultTextView = findViewById(R.id.resultTextView)
        equalsButton = findViewById(R.id.equalsButton)
        clearButton = findViewById(R.id.clear_button)
    }

    fun numberClick(view: View) {

        if (view is Button) {

            val number = view.text.toString()
            var result = resultTextView.text.toString()

            if (result == "0") {
                result = ""
            }

            resultTextView.text = result + number

        }

    }

    fun operationClick(view: View) {

        if (view is Button) {

            if (resultTextView.text.toString().isNotEmpty()) {
                operand = resultTextView.text.toString().toDouble()
            }

            operation = view.text.toString()

            resultTextView.text = ""

        }

    }

    fun makeSqrt(view: View) {
        if (view is Button) {
            if (resultTextView.text.isNotEmpty()) {
                operand = resultTextView.text.toString().toDouble()
                resultTextView.text = sqrt(operand).toString()
            }
        }
    }

    private fun clearTextFromResult(): Boolean {
        resultTextView.text = ""
        return true
    }

    private fun clearButton() {
        var result = resultTextView.text.toString()
        result = result.dropLast(1)
        resultTextView.text = result
    }

    fun insertPeriod(view: View) {
        if (resultTextView.text.isNotEmpty()) {
                if (!resultTextView.text.contains('.')) {
                    var result = resultTextView.text.toString()
                    result = "$result."
                    resultTextView.text = result
                }
        }
    }


}