package com.example.practical3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val MIN: Int = 0
const val MAX: Int = 50

class GuessGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigationWrapper(currentActivity = GuessGameActivity::class.java) { paddingValues ->
                GuessGame(paddingValues)
            }
        }
    }
}

@Composable
fun GuessGame(paddingValues: PaddingValues) {
    val INITIAL_MESSAGE: String = "Нова гра! Вгадайте число від $MIN до $MAX";

    val (input, setInput) = remember { mutableStateOf("") }
    val (resultText, setResultText) = remember { mutableStateOf(INITIAL_MESSAGE) }
    var randomNumber by remember { mutableIntStateOf((MIN..MAX).random()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = setInput,
            label = { Text("Введіть ваше число") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    val guess = input.toIntOrNull()

                    if (guess == null) {
                        setResultText("Будь ласка, введіть коректне число")
                        return@Button
                    }
                    if(guess !in MIN..MAX) {
                        setResultText("Будь ласка, введіть число з діапазону від $MIN до $MAX")
                        return@Button
                    }

                    if (guess < randomNumber) {
                        setResultText("Загадане число більше ніж $guess")
                    } else if (guess > randomNumber) {
                        setResultText("Загадане число менше ніж $guess")
                    } else {
                        setResultText("Ви вгадали! Це дійсно $guess")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Вгадати")
            }
            Button(
                onClick = {
                    randomNumber = (MIN..MAX).random()
                    setInput("")
                    setResultText(INITIAL_MESSAGE)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Нова гра")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = resultText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp
        )
    }
}
