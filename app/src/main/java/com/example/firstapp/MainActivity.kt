package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ui.theme.FirstAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAPPTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("0") }

    val buttons = listOf(
        listOf("AC", "+/-", "%", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "=")
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            OutlinedTextField(
                value = input,
                textStyle = TextStyle(fontSize = 24.sp),
                onValueChange = {newText -> input = newText },
                modifier = Modifier.fillMaxWidth(),

                )
            Text(
                result,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column{
            buttons.forEach { row ->
                Row{
                    row.forEach { buttonText ->
                        val type = when (buttonText) {
                            "AC", "+/-", "%" -> CalculatorButtonType.Action
                            "÷", "×", "-", "+" -> CalculatorButtonType.Operation
                            "=" -> CalculatorButtonType.Equals
                            else -> CalculatorButtonType.Number
                        }

                        CalculatorButton(
                            text = buttonText,
                            type = type,
                            onClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    type: CalculatorButtonType,
    onClick: () -> Unit
){
    val containerColor = when (type) {
        CalculatorButtonType.Number ->
            MaterialTheme.colorScheme.surfaceVariant

        CalculatorButtonType.Operation ->
            MaterialTheme.colorScheme.primary

        CalculatorButtonType.Action ->
            MaterialTheme.colorScheme.secondary

        CalculatorButtonType.Equals ->
            MaterialTheme.colorScheme.tertiary
    }

    Button(
        modifier = Modifier.size(100.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Text(text, fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    FirstAPPTheme {
        MainScreen()
    }
}