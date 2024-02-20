package com.keak.instagramstory

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InstagramStory(
    modifier: Modifier = Modifier,
    imageList: List<String>,
    screenDuration: Long = 4000,
    durationEnd: () -> Unit = {},
    beforeDurationEnd: () -> Unit = {},
    content: @Composable (page: Int) -> Unit,
) {
    var currentStep by remember { mutableIntStateOf(0) }
    var paused by remember {
        mutableStateOf(false)
    }
    val isPressed = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(
        pageCount = { imageList.size },
    )
    val percent = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        percent.snapTo(0f)
        currentStep = pagerState.currentPage + 1
        delay(screenDuration) // Screen duration
    }
    Column(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            val maxWidth = this.size.width
            detectTapGestures(
                onPress = {
                    val pressStartTime = System.currentTimeMillis()
                    isPressed.value = true
                    this.tryAwaitRelease()
                    paused = false
                    val pressEndTime = System.currentTimeMillis()
                    val totalPressTime = pressEndTime - pressStartTime
                    if (totalPressTime < 200) {
                        val isTapOnRightThreeQuarters = (it.x > (maxWidth / 4))
                        if (isTapOnRightThreeQuarters) {
                            if ((currentStep >= imageList.size).not()) {
                                currentStep = pagerState.currentPage + 1
                                pagerState.animateScrollToPage(currentStep)
                            }
                        } else {
                            if ((currentStep <= 1).not()) {
                                currentStep = pagerState.currentPage - 1
                                pagerState.animateScrollToPage(currentStep)
                            }
                        }
                    }
                    isPressed.value = false
                },
                onLongPress = {
                    paused = true
                }
            )
        }) {

        Box(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(8.dp))
            InstagramSlicedProgressBar(
                modifier = Modifier
                    .height(48.dp)
                    .padding(24.dp, 0.dp)
                    .zIndex(2f),
                steps = imageList.size,
                currentStep = currentStep,
                paused = paused,
                onFinished = {
                    durationEnd.invoke()
                    scope.launch {
                        if ((currentStep >= imageList.size).not()) {
                            pagerState.animateScrollToPage(currentStep)
                        }
                    }
                },
                beforeDurationEnd = {
                    beforeDurationEnd.invoke()
                },
                percent = percent
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                userScrollEnabled = false,
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    orientation = Orientation.Horizontal
                )
            ) { page ->
                content.invoke(page)
            }
        }
    }
}
