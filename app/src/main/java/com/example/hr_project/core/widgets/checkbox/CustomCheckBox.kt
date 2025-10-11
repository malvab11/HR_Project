package com.example.hr_project.core.widgets.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hr_project.ui.theme.Purple400
import com.example.hr_project.ui.theme.Purple50

@Composable
fun CustomCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    borderColor: Color = Purple400,
    checkedColor: Color = Purple400,
    uncheckedColor: Color = Purple50,
    checkmarkColor: Color = Color.White,
    uncheckmarkColor: Color = Purple400,
    cornerRadius: Dp = 6.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                color =
                    if (checked) checkedColor else uncheckedColor
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable(onClick = { onCheckedChange(!checked) }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = if (checked) checkmarkColor else uncheckmarkColor,
            modifier = Modifier.size(size * 0.7f)
        )

    }
}

