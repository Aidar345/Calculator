package com.example.firstapp.viewmodel

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

                val input = _inputText.value + button.text

                val operators = listOf("+", "-", "×", "÷")
                val lastOperatorMatch = input.findLastAnyOf(operators)

                val lastNumber = if (lastOperatorMatch != null) {
                    input.substring(lastOperatorMatch.first + 1)
                } else {
                    input
                }

                val result = calculate(
                    firstNumber = currentNumber!!,
                    secondNumber = nextlastNumberNumber!!,
                    operation = operation!!
                )

                if (result != null) {
                    _result.value = result.toString()
                }
            }

            CalculatorButtonType.Operation -> {
                val input = _inputText.value + button.text

                val operators = listOf("+", "-", "×", "÷")
                val lastOperatorMatch = input.findLastAnyOf(operators)

                val lastNumber = if (lastOperatorMatch != null) {
                    input.substring(lastOperatorMatch.first + 1)
                } else {
                    input
                }

                _inputText.value = input

                currentNumber = lastNumber.toDoubleOrNull()

                if (accumulator == null) {
                    accumulator = lastNumber.toDouble()
                } else {
                    val result = calculate(
                        firstNumber = accumulator!!,
                        secondNumber = currentNumber!!,
                        operation = operation!!
                    )

                    accumulator = result
                    operation = button.text
                }
            }

            CalculatorButtonType.Action -> {
                when (button.text) {
                    "AC" -> {
                        _inputText.value = ""
                        _result.value = "0"
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