package com.example.firstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstapp.CalculatorButtonType
import com.example.firstapp.model.CalculatorButtonData

class CalculatorViewModel: ViewModel() {
    private val _inputText = MutableLiveData("")
    val inputText : LiveData<String> = _inputText

    private val _result = MutableLiveData("0")
    val result: LiveData<String> = _result

    fun onButtonClick(button: CalculatorButtonData) {
        when (button.type) {
            CalculatorButtonType.Number -> _inputText.value += button.text

            CalculatorButtonType.Operation -> {
                when (button.text) {
                    "÷" -> {

                    }

                    "×" -> {

                    }

                    "+" -> {

                    }

                    "-" -> {

                    }
                }
            }

            CalculatorButtonType.Action -> {
                when (button.text) {
                    "AC" -> {
                        _inputText.value = ""
                        _inputText.value = "0"
                    }

                    "%" -> {

                    }

                    "⌫" -> {
                        _inputText.value = _inputText.value?.dropLast(1)
                    }
                }
            }

            CalculatorButtonType.Equals -> {

            }
        }
    }
}