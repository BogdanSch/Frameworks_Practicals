package com.example.practical3

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.pow

class CalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigationWrapper(currentActivity = CalculatorActivity::class.java) { paddingValues ->
                CalculatorScreen(paddingValues)
            }
        }
    }
}

@Composable
fun CalculatorScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current
    val fileName = "calculator_history.txt"

    val (num1, setNum1) = remember { mutableStateOf("") }
    val (num2, setNum2) = remember { mutableStateOf("") }
    val (result, setResult) = remember { mutableStateOf("") }
    val (history, setHistory) = remember { mutableStateOf(loadHistory(context, fileName)) }

    fun performOperation(op: String) {
        val val1 = num1.toDoubleOrNull()
        val val2 = num2.toDoubleOrNull()

        if ((val1 == null) || (val2 == null)) {
            setResult("Помилка: введіть числа")
            return
        }

        val calculation = when (op) {
            "+" -> val1 + val2
            "-" -> val1 - val2
            "*" -> val1 * val2
            "/" -> if (val2 != 0.0) val1 / val2 else Double.NaN
            "%" -> val1 % val2
            "^" -> val1.pow(val2)
            else -> 0.0
        }

        var resultString = calculation.toString()
        if(calculation.isNaN()) {
            resultString = "Помилка: ділення на 0"
        }
        
        if (!calculation.isNaN()) {
            setResult(resultString)
            val entry = "$val1 $op $val2 = $resultString"
            saveToFile(context, fileName, entry)
            setHistory(loadHistory(context, fileName))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = num1,
            onValueChange = setNum1,
            label = { Text("Число 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = num2,
            onValueChange = setNum2,
            label = { Text("Число 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("+", "-", "*").forEach { op ->
                Button(onClick = { performOperation(op) }, modifier = Modifier.weight(1f).padding(4.dp)) {
                    Text(op)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("/", "%", "^").forEach { op ->
                Button(onClick = { performOperation(op) }, modifier = Modifier.weight(1f).padding(4.dp)) {
                    Text(op)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Результат: $result", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Історія операцій:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            TextButton(onClick = {
                clearHistory(context, fileName)
                setHistory(emptyList())
            }) {
                Text("Очистити")
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(history.reversed()) { entry ->
                Text(text = entry, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

fun saveToFile(context: Context, fileName: String, content: String) {
    try {
        val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
        fileOutputStream.write((content + "\n").toByteArray())
        fileOutputStream.close()
    } catch (_: IOException) {}
}

fun loadHistory(context: Context, fileName: String): List<String> {
    val file = File(context.filesDir, fileName)
    if (!file.exists())
        return emptyList()

    return try {
        file.readLines()
    } catch (_: IOException) {
        emptyList()
    }
}

fun clearHistory(context: Context, fileName: String) {
    val file = File(context.filesDir, fileName)
    if (file.exists()) {
        file.delete()
    }
}