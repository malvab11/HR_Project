package com.example.hr_project.core.widgets.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.hr_project.ui.theme.Gray400
import com.example.hr_project.ui.theme.Purple400

@Composable
fun SimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    leadingIcon: Int,
    placeholder: Int,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = Purple400
            )
        },
        placeholder = {
            Text(
                stringResource(
                    placeholder
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = Gray400
            )
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple400,
            unfocusedBorderColor = Gray400
        )
    )
}

@Composable
fun ActionOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    leadingIcon: Int,
    placeholder: Int,
    trailingIcon: Int,
    trailingIcon2: Int,
    keyboardType: KeyboardType,
    isVisible: Boolean = false,
    onIconClick: () -> Unit,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = Purple400
            )
        },
        placeholder = {
            Text(
                stringResource(
                    placeholder
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = Gray400
            )
        },
        trailingIcon = {
            IconButton(onClick = { onIconClick() }) {
                Icon(
                    painter = if (isVisible) painterResource(trailingIcon) else painterResource(
                        trailingIcon2
                    ),
                    contentDescription = null,
                    tint = Purple400
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple400,
            unfocusedBorderColor = Gray400
        )
    )
}