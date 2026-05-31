package com.example.practical3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigationWrapper(currentActivity = MainActivity::class.java) { paddingValues ->
                DifferenceCalculator(paddingValues)
            }
        }
    }
}

@Composable
fun DifferenceCalculator(paddingValues: PaddingValues) {
    val (firstNumber, setFirstNumber) = remember { mutableStateOf("") }
    val (secondNumber, setSecondNumber) = remember { mutableStateOf("") }
    val (resultText, setResultText) = remember { mutableStateOf("Результат:") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = firstNumber,
            onValueChange = { setFirstNumber(it) },
            label = { Text("Введіть перше число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = secondNumber,
            onValueChange = { setSecondNumber(it) },
            label = { Text("Введіть друге число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                val num1 = firstNumber.toDoubleOrNull()
                val num2 = secondNumber.toDoubleOrNull()

                if (num1 != null && num2 != null) {
                    val difference = num1 - num2
                    setResultText("Результат: $difference")
                } else {
                    setResultText("Будь ласка, введіть коректні числа")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити різницю")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = resultText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}