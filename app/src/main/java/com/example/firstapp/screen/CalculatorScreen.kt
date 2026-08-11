package com.example.firstapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.firstapp.model.CalculatorButtonData
import com.example.firstapp.CalculatorButtonType
import com.example.firstapp.ui.theme.FirstAPPTheme
import com.example.firstapp.viewmodel.CalculatorViewModel

val buttons = listOf(
    listOf(
        CalculatorButtonData("AC", CalculatorButtonType.Action),
        CalculatorButtonData("()", CalculatorButtonType.Action),
        CalculatorButtonData("%", CalculatorButtonType.Action),
        CalculatorButtonData("÷", CalculatorButtonType.Operation)
    ),
    listOf(
        CalculatorButtonData("7", CalculatorButtonType.Number),
        CalculatorButtonData("8", CalculatorButtonType.Number),
        CalculatorButtonData("9", CalculatorButtonType.Number),
        CalculatorButtonData("×", CalculatorButtonType.Operation)
    ),
    listOf(
        CalculatorButtonData("4", CalculatorButtonType.Number),
        CalculatorButtonData("5", CalculatorButtonType.Number),
        CalculatorButtonData("6", CalculatorButtonType.Number),
        CalculatorButtonData("-", CalculatorButtonType.Operation)
    ),
    listOf(
        CalculatorButtonData("1", CalculatorButtonType.Number),
        CalculatorButtonData("2", CalculatorButtonType.Number),
        CalculatorButtonData("3", CalculatorButtonType.Number),
        CalculatorButtonData("+", CalculatorButtonType.Operation)
    ),
    listOf(
        CalculatorButtonData("0", CalculatorButtonType.Number),
        CalculatorButtonData(".", CalculatorButtonType.Number),
        CalculatorButtonData("⌫", CalculatorButtonType.Action),
        CalculatorButtonData("=", CalculatorButtonType.Equals)
    )
)

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, viewModel: CalculatorViewModel){
    Box(modifier = modifier){
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ){
            TextField(
                value = "123+123",
                textStyle = TextStyle(
                    fontSize = 32.sp,
                    textAlign = TextAlign.End
                ),
                onValueChange = {/*newText -> input = newText */},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "246",
                style = TextStyle(
                    fontSize = 64.sp,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Column{
                buttons.forEach { row ->
                    Row{
                        row.forEach { button ->
                            CalculatorButton(
                                text = button.text,
                                type = button.type,
                                onClick = {
                                    viewModel.onButtonClick(button)
                                }
                            )
                        }
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