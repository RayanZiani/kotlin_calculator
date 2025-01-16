package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var resultEditText: EditText
    private var currentExpression = StringBuilder()
    private var lastCharWasOperator = false
    private var hasDecimalPoint = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultEditText = findViewById(R.id.resultEditText)
        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        // Configuration des listeners pour les chiffres
        val numberButtons = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                val number = (it as Button).text.toString()
                appendNumber(number)
            }
        }

        // Configuration des listeners pour les opérations
        val operatorButtons = mapOf(
            R.id.btnPlus to "+",
            R.id.btnMinus to "-",
            R.id.btnMultiply to "×",
            R.id.btnDivide to "÷",
            R.id.btnPercent to "%"
        )

        operatorButtons.forEach { (id, operator) ->
            findViewById<Button>(id).setOnClickListener {
                appendOperator(operator)
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener {
            appendDecimalPoint()
        }

        // Boutons spéciaux
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            clearExpression()
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            deleteLastCharacter()
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            calculateResult()
        }
    }

    private fun appendNumber(number: String) {
        currentExpression.append(number)
        lastCharWasOperator = false
        updateDisplay()
    }

    private fun appendOperator(operator: String) {
        if (currentExpression.isEmpty() && operator != "-") {
            return
        }

        if (lastCharWasOperator) {
            currentExpression.deleteCharAt(currentExpression.length - 1)
        }

        currentExpression.append(operator)
        lastCharWasOperator = true
        hasDecimalPoint = false
        updateDisplay()
    }

    private fun appendDecimalPoint() {
        if (!hasDecimalPoint && !lastCharWasOperator) {
            if (currentExpression.isEmpty() || lastCharWasOperator) {
                currentExpression.append("0")
            }
            currentExpression.append(",")
            hasDecimalPoint = true
            lastCharWasOperator = false
            updateDisplay()
        }
    }

    private fun clearExpression() {
        currentExpression.clear()
        lastCharWasOperator = false
        hasDecimalPoint = false
        updateDisplay()
    }

    private fun deleteLastCharacter() {
        if (currentExpression.isNotEmpty()) {
            val lastChar = currentExpression.last()
            currentExpression.deleteCharAt(currentExpression.length - 1)

            if (lastChar == ',') {
                hasDecimalPoint = false
            }

            lastCharWasOperator = currentExpression.isNotEmpty() &&
                    isOperator(currentExpression.last())

            updateDisplay()
        }
    }

    private fun isOperator(char: Char): Boolean {
        return char in setOf('+', '-', '×', '÷', '%')
    }

    private fun updateDisplay() {
        resultEditText.setText(currentExpression.toString())
    }

    private fun calculateResult() {
        if (currentExpression.isEmpty()) return

        // Vérifie si l'expression se termine par un opérateur
        if (lastCharWasOperator) {
            currentExpression.deleteCharAt(currentExpression.length - 1)
        }

        try {
            val expression = currentExpression.toString()
                .replace("×", "*")
                .replace("÷", "/")
                .replace("%", "/100")
                .replace(",", ".")

            val result = evaluateExpression(expression)

            // Formatage du résultat
            val formattedResult = formatResult(result)

            currentExpression.clear()
            currentExpression.append(formattedResult)

            // Mise à jour des états
            lastCharWasOperator = false
            hasDecimalPoint = formattedResult.contains(",")

            updateDisplay()
        } catch (e: Exception) {
            Toast.makeText(this, "Expression invalide", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        return ExpressionBuilder(expression)
            .build()
            .evaluate()
    }

    private fun formatResult(result: Double): String {
        val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
            decimalSeparator = ','
        }

        val format = DecimalFormat("#,##0.###", symbols).apply {
            isDecimalSeparatorAlwaysShown = false
            minimumFractionDigits = 0
            maximumFractionDigits = 8
        }

        return format.format(result)
    }
}