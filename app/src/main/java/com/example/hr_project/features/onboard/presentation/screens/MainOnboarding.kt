package com.example.hr_project.features.onboard.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hr_project.R
import com.example.hr_project.core.widgets.buttons.FilledButtons
import com.example.hr_project.core.widgets.buttons.OutlinedButtons
import com.example.hr_project.ui.theme.Black
import com.example.hr_project.ui.theme.Purple200
import com.example.hr_project.ui.theme.Purple500
import com.example.hr_project.ui.theme.shapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainOnboarding(modifier: Modifier) {

    val pages = listOf(
        OnboardingData(
            title = R.string.onboarding_title_1,
            subTitle = R.string.onboarding_sub_title_1,
            painter = R.drawable.page1_img
        ),
        OnboardingData(
            title = R.string.onboarding_title_2,
            subTitle = R.string.onboarding_sub_title_2,
            painter = R.drawable.page2_img
        ),
        OnboardingData(
            title = R.string.onboarding_title_3,
            subTitle = R.string.onboarding_sub_title_3,
            painter = R.drawable.page3_img
        ),
        OnboardingData(
            title = R.string.onboarding_title_4,
            subTitle = R.string.onboarding_sub_title_4,
            painter = R.drawable.page4_img
        )
    )
    val state = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = state,
            modifier = Modifier.weight(1f),
        ) { page ->
            val (title, subTitle, painter) = pages[page]
            OnboardingPage(title = title, subTitle = subTitle, painter = painter)
        }
        PagerDotsIndicator(
            totalIndicators = pages.size,
            selectedIndex = state.currentPage,
        )
        ButtonsSection(coroutineScope = coroutineScope, pagerState = state, totalPages = pages.size)
    }
}

private data class OnboardingData(
    val title: Int,
    val subTitle: Int,
    val painter: Int
)

@Composable
private fun OnboardingPage(modifier: Modifier = Modifier, title: Int, subTitle: Int, painter: Int) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Purple500,
                            Purple200,
                            MaterialTheme.colorScheme.background
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(painter),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
        }
        TitleSection(title = title, subTitle = subTitle)
    }
}

@Composable
private fun TitleSection(modifier: Modifier = Modifier, title: Int, subTitle: Int) {
    Column(
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp, bottom = 16.dp, top = 64.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.headlineSmall,
            color = Black
        )
        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(subTitle),
            style = MaterialTheme.typography.bodyMedium,
            color = Black
        )
    }
}

@Composable
private fun PagerDotsIndicator(
    totalIndicators: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    inactiveColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(
        modifier = modifier.padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalIndicators) { index ->
            val color = if (index == selectedIndex) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .size(height = 4.dp, width = 20.dp)
                    .clip(shape = shapes.medium)
                    .background(color)
            )
        }
    }
}

@Composable
private fun ButtonsSection(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    totalPages: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledButtons(stringResource = R.string.onboarding_button_next) {
            if (pagerState.currentPage + 1 != totalPages) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(
                        page = pagerState.currentPage + 1
                    )
                }
            } else {
                Log.i("Log In", "Acá aparecera el logueo")
            }
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButtons(stringResource = R.string.onboarding_button_skip) { }
    }
}
