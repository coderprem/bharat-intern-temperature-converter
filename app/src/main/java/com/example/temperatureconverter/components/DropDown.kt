package com.example.temperatureconverter.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(dWidth: Dp = 300.dp, onValChange: (String) -> Unit = {}) {
    Surface(
        modifier = Modifier.width(dWidth).padding(12.dp),
    ) {
        val units = listOf("Celsius", "Fahrenheit", "Kelvin")
        val isExpanded = remember {
            mutableStateOf(false)
        }
        val unit = remember {
            mutableStateOf("")
        }

        ExposedDropdownMenuBox(expanded = isExpanded.value, onExpandedChange = {
            isExpanded.value = it
        }) {

            TextField(
                value = unit.value,
                onValueChange = { unit.value = it },
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                },
            )

            ExposedDropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }) {
                DropdownMenuItem(text = { Text(text = units[0]) }, onClick = {
                    unit.value = "Celsius"
//                    Log.d("unit", unit.value)
                    isExpanded.value = false
                })
                DropdownMenuItem(text = { Text(text = units[1]) }, onClick = {
                    unit.value = "Fahrenheit"
//                    Log.d("unit", unit.value)
                    onValChange(unit.value.trim())
                    isExpanded.value = false
                })

                DropdownMenuItem(text = { Text(text = units[2]) }, onClick = {
                    unit.value = "Kelvin"
//                    Log.d("unit", unit.value)
                    onValChange(unit.value.trim())
                    isExpanded.value = false
                })

            }
        }
    }
}