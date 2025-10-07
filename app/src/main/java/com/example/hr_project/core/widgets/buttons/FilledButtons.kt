package com.example.hr_project.core.widgets.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hr_project.ui.theme.Purple200
import com.example.hr_project.ui.theme.Purple400
import com.example.hr_project.ui.theme.Purple500
import com.example.hr_project.ui.theme.Purple600
import com.example.hr_project.ui.theme.Purple700
import com.example.hr_project.ui.theme.White

@Composable
fun FilledButtons(
    modifier: Modifier = Modifier,
    @StringRes stringResource: Int,
    onClick: () -> Unit
) {

    val gradient = Brush.linearGradient(
        colors = listOf(
            Purple400,
            Purple500,
            Purple700
        ),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(brush = gradient)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = White
            ),
        ) {
            Text(
                text = stringResource(id = stringResource),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}