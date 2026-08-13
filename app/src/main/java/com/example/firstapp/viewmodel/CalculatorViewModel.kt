package com.example.firstapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapp.CalculatorButtonType
import com.example.firstapp.model.CalculatorButtonData

class CalculatorViewModel: ViewModel() {
    private val _inputText = MutableLiveData("")
    val inputText: LiveData<String> = _inputText

    private val _result = MutableLiveData("0")
    val result: LiveData<String> = _result

    private var currentNumber: Double? = null
    private var operation: String? = null
    private var accumulator: Double? = null

    fun onButtonClick(button: CalculatorButtonData) {
        when (button.type) {
            CalculatorButtonType.Number -> _inputText.value += button.text
            CalculatorButtonType.Equals -> {
                val input = _inputText.value ?: ""

                currentNumber = getLastNumber(input)

                Log.d("CALCULATOR", "input = $input")
                Log.d("CALCULATOR", "accumulator = $accumulator")
                Log.d("CALCULATOR", "currentNumber = $currentNumber")
                Log.d("CALCULATOR", "operation = $operation")

                val result = calculate(
                    firstNumber = accumulator ?: return,
                    secondNumber = currentNumber ?: return,
                    operation = operation ?: return
                )

                Log.d("CALCULATOR", "result = $result")

                if (result != null) {
                    _result.value = result.toString()
                }
            }

            CalculatorButtonType.Operation -> {
                val input = _inputText.value ?: ""

                if (accumulator == null) {
                    accumulator = input.toDoubleOrNull()
                    operation = button.text
                }

                _inputText.value =  input + button.text
            }

            CalculatorButtonType.Action -> {
                when (button.text) {
                    "AC" -> {
                        _inputText.value = ""
                        _result.value = "0"
                        accumulator = null
                        currentNumber = null
                        operation = null
                    }

                    "%" -> {

                    }

                    "⌫" -> {
                        _inputText.value = _inputText.value?.dropLast(1)
                    }
                }
            }
        }
    }

    private fun getLastNumber(input: String): Double? {
        val operators = listOf("+", "-", "×", "÷")
        val lastOperatorMatch = input.findLastAnyOf(operators)

        val lastNumber = if (lastOperatorMatch != null) {
            input.substring(lastOperatorMatch.first + 1)
        } else {
            input
        }

        return lastNumber.toDoubleOrNull()
    }

    private fun calculate(
        firstNumber: Double,
        secondNumber: Double,
        operation: String
    ): Double? {
        return when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "×" -> firstNumber * secondNumber
            "÷" -> {
                if (secondNumber == 0.0) {
                    null
                } else {
                    firstNumber / secondNumber
                }
            }
            else -> null
        }
    }
}