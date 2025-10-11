package com.example.hr_project.features.auth.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hr_project.R
import com.example.hr_project.core.widgets.buttons.FilledButtons
import com.example.hr_project.core.widgets.buttons.OutlinedButtons
import com.example.hr_project.core.widgets.checkbox.CustomCheckBox
import com.example.hr_project.core.widgets.fields.ActionOutlinedTextField
import com.example.hr_project.core.widgets.fields.SimpleOutlinedTextField
import com.example.hr_project.features.auth.presentation.models.AuthMethodsUi
import com.example.hr_project.ui.theme.Black
import com.example.hr_project.ui.theme.Gray400
import com.example.hr_project.ui.theme.Gray600

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthContent(
    modifier: Modifier = Modifier,
) {

    var selectedMethod by remember { mutableStateOf(AuthMethodsUi.EMAIL) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleSection(
            title = R.string.auth_title,
            subTitle = R.string.auth_sub_title
        )
        Spacer(Modifier.height(8.dp))
        InputSection(
            selectedMethod = selectedMethod
        )
        Spacer(Modifier.height(12.dp))
        OptionsSection()
        Spacer(Modifier.height(24.dp))
        ButtonsSection(
            selectedMethod = selectedMethod,
            onSignIn = {
                when(selectedMethod){
                    AuthMethodsUi.EMAIL -> {
                        Log.i("TAG", "AuthContent: Email")}
                    AuthMethodsUi.EMPLOYEE_ID -> {Log.i("TAG", "AuthContent: Employee")}
                    AuthMethodsUi.PHONE_NUMBER -> {Log.i("TAG", "AuthContent: Phone")}
                }
            },
            onSelectedMethod = { selectedMethod = it }
        )
        Spacer(Modifier.height(22.dp))
        FooterSection()
    }
}
@Composable
private fun TitleSection(
    modifier: Modifier = Modifier,
    title: Int,
    subTitle: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Black
        )
        Text(
            text = stringResource(subTitle),
            style = MaterialTheme.typography.bodyMedium,
            color = Black
        )
    }
}

@Composable
private fun InputSection(
    modifier: Modifier = Modifier,
    selectedMethod: AuthMethodsUi,
) {
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var employeeID by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (selectedMethod) {
            AuthMethodsUi.EMAIL -> {
                InputField(
                    label = R.string.auth_input_1_label,
                    placeholder = R.string.auth_input_1_placeholder,
                    icon = R.drawable.ic_mail,
                    value = email,
                    onValueChange = { email = it },
                    keyboardType = KeyboardType.Email
                )
                Spacer(Modifier.height(24.dp))
                PasswordField(
                    label = R.string.auth_input_2_label,
                    placeholder = R.string.auth_input_2_placeholder,
                    value = password,
                    onValueChange = { password = it },
                    isVisible = isVisible,
                    onVisibilityToggle = { isVisible = !isVisible }
                )
            }

            AuthMethodsUi.EMPLOYEE_ID -> {
                InputField(
                    label = R.string.auth_input_3_label,
                    placeholder = R.string.auth_input_3_placeholder,
                    icon = R.drawable.ic_employee,
                    value = employeeID,
                    onValueChange = { employeeID = it },
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(24.dp))
                PasswordField(
                    label = R.string.auth_input_2_label,
                    placeholder = R.string.auth_input_2_placeholder,
                    value = password,
                    onValueChange = { password = it },
                    isVisible = isVisible,
                    onVisibilityToggle = { isVisible = !isVisible }
                )
            }

            AuthMethodsUi.PHONE_NUMBER -> InputField(
                label = R.string.auth_input_4_label,
                placeholder = R.string.auth_input_4_placeholder,
                icon = R.drawable.ic_phone,
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                keyboardType = KeyboardType.Number
            )
        }
    }
}

@Composable
private fun InputField(
    label: Int,
    placeholder: Int,
    icon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    Column {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodySmall,
            color = Gray600
        )
        Spacer(Modifier.height(3.dp))
        SimpleOutlinedTextField(
            value = value,
            leadingIcon = icon,
            placeholder = placeholder,
            keyboardType = keyboardType,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun PasswordField(
    label: Int,
    placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Column {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodySmall,
            color = Gray600
        )
        Spacer(Modifier.height(3.dp))
        ActionOutlinedTextField(
            value = value,
            leadingIcon = R.drawable.ic_finger_print,
            placeholder = placeholder,
            trailingIcon = R.drawable.ic_visibility,
            trailingIcon2 = R.drawable.ic_hide,
            keyboardType = KeyboardType.Password,
            isVisible = isVisible,
            onIconClick = onVisibilityToggle,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun OptionsSection(modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomCheckBox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.auth_checkbox_label),
                style = MaterialTheme.typography.bodySmall,
                color = Black
            )
        }

        Text(
            text = stringResource(R.string.auth_forgot_password),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ButtonsSection(
    modifier: Modifier = Modifier,
    selectedMethod: AuthMethodsUi,
    onSignIn: () -> Unit,
    onSelectedMethod: (AuthMethodsUi) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FilledButtons(
            stringResource = R.string.auth_sign_in_button,
            onClick = onSignIn
        )

        OrDivider()
        if (selectedMethod != AuthMethodsUi.EMAIL) {
            OutlinedButtons(
                stringResource = R.string.auth_sign_in_with_email,
                painterResource = R.drawable.ic_mail
            ) { onSelectedMethod(AuthMethodsUi.EMAIL) }
        }
        if (selectedMethod != AuthMethodsUi.EMPLOYEE_ID) {
            OutlinedButtons(
                stringResource = R.string.auth_sign_in_with_employee_id,
                painterResource = R.drawable.ic_employee
            ) { onSelectedMethod(AuthMethodsUi.EMPLOYEE_ID) }
        }
        if (selectedMethod != AuthMethodsUi.PHONE_NUMBER) {
            OutlinedButtons(
                stringResource = R.string.auth_sign_in_with_phone,
                painterResource = R.drawable.ic_phone
            ) { onSelectedMethod(AuthMethodsUi.PHONE_NUMBER) }
        }
    }
}

@Composable
private fun FooterSection(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.auth_dont_have_account_label),
            style = MaterialTheme.typography.labelSmall,
            color = Black
        )
        Spacer(Modifier.width(2.dp))
        Text(
            stringResource(R.string.auth_clickable_regiter_text),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/* ------------------------------- */
/* ---------- DIVIDER ------------ */
/* ------------------------------- */

@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = Gray400
        )
        Text(
            text = stringResource(R.string.auth_division_label_side),
            style = MaterialTheme.typography.labelMedium,
            color = Gray400,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Divider(
            modifier = Modifier.weight(1f),
            color = Gray400
        )
    }
}
