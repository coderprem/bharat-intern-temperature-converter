package com.example.temperatureconverter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "",
    isSingleLine: Boolean,
    isEnable: Boolean,
    isReadOnly: Boolean,
    keyBoardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier.padding(all = 12.dp).fillMaxWidth(),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Outlined.AttachMoney,
//                contentDescription = "Money sign"
//            )
//        },
        singleLine = isSingleLine,
        readOnly = isReadOnly,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = isEnable,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType, imeAction = imeAction),
        keyboardActions = onAction,
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF673AB7),
            unfocusedBorderColor = Color(0xFF673AB7),
        )

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String = "",
    isSingleLine: Boolean = true,
    isEnable: Boolean = false,
    isReadOnly: Boolean = false,
    keyBoardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier.padding(all = 12.dp).fillMaxWidth(),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
//        leadingIcon = {
//            Icon(
//                imageVector = Icons.Outlined.AttachMoney,
//                contentDescription = "Money sign"
//            )
//        },
        singleLine = isSingleLine,
        readOnly = isReadOnly,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = isEnable,
//        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType, imeAction = imeAction),
//        keyboardActions = onAction,
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF673AB7),
            unfocusedBorderColor = Color(0xFF673AB7),
        )

    )
}