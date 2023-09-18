package com.example.temperatureconverter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.temperatureconverter.ui.theme.TemperatureConverterTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.temperatureconverter.components.InputField
import com.example.temperatureconverter.components.DropDown
import com.example.temperatureconverter.components.OutputField
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainComponent()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun MainComponent() {
    val temp = remember {
        mutableStateOf("")
    }
    val validState = remember(temp.value) {
        temp.value.trim().isNotEmpty()
    }
    val answer = remember {
        mutableStateOf("")
    }
    val inputUnit = remember {
        mutableStateOf("Celsius")
    }
    val outputUnit = remember {
        mutableStateOf("Fahrenheit")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            DropDown(200.dp) {
                inputUnit.value = it
            }
            InputField(
                valueState = temp,
                isSingleLine = true,
                labelId = "Input here",
                isEnable = true,
                isReadOnly = false,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    keyboardController?.hide()
                    val output =
                        convertTemperature(temp.value.toDouble(), inputUnit.value, outputUnit.value)
                    answer.value = output.toString()
                    Log.d("tag", output.toString())
                }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            DropDown(200.dp) {
                outputUnit.value = it
            }
            OutputField(
                valueState = answer,
                isSingleLine = true,
                isEnable = true,
                isReadOnly = false
            )
        }
    }
}

fun convertTemperature(value: Double?, inputUnit: String, outputUnit: String): Double {
    if (value == null) {
        return 0.0 // Handle invalid input gracefully
    }

    // Perform temperature conversion based on the selected input and output units
    val convertedValue = when (inputUnit) {
        "Celsius" -> when (outputUnit) {
            "Celsius" -> value
            "Fahrenheit" -> (value * 9.0 / 5.0 + 32).roundToTwoDecimalPlaces()
            "Kelvin" -> (value + 273.15).roundToTwoDecimalPlaces()
            else -> 0.0 // Handle invalid output unit
        }
        "Fahrenheit" -> when (outputUnit) {
            "Celsius" -> ((value - 32) * 5.0 / 9.0).roundToTwoDecimalPlaces()
            "Fahrenheit" -> value
            "Kelvin" -> ((value - 32) * 5.0 / 9.0 + 273.15).roundToTwoDecimalPlaces()
            else -> 0.0 // Handle invalid output unit
        }
        "Kelvin" -> when (outputUnit) {
            "Celsius" -> (value - 273.15).roundToTwoDecimalPlaces()
            "Fahrenheit" -> ((value - 273.15) * 9.0 / 5.0 + 32).roundToTwoDecimalPlaces()
            "Kelvin" -> value
            else -> 0.0 // Handle invalid output unit
        }
        else -> 0.0 // Handle invalid input unit
    }

    return convertedValue
}

// Extension function to round a Double to two decimal places
fun Double.roundToTwoDecimalPlaces(): Double {
    return (this * 100.0).roundToInt() / 100.0
}