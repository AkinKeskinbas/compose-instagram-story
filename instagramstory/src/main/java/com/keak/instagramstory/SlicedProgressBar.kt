package com.keak.instagramstory

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun InstagramSlicedProgressBar(
    modifier: Modifier = Modifier,
    steps: Int,
    currentStep: Int,
    paused: Boolean,
    onFinished: () -> Unit,
    beforeDurationEnd: () -> Unit,
    percent: Animatable<Float, AnimationVector1D>
) {
    LaunchedEffect(currentStep, paused) {
        if (paused.not()) {
            percent.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = (8000 * (1f - percent.value)).toInt(),
                    easing = LinearEasing
                )
            ) {
                //before time end
                if (value in 0.80f..0.90f) {
                    beforeDurationEnd.invoke()
                }
            }
            if (percent.value == 1f) {
                onFinished()
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,

        ) {
        for (index in 1..steps) {
            Row(
                modifier = Modifier
                    .height(4.dp)
                    .clip(RoundedCornerShape(50, 50, 50, 50))
                    .weight(1f)
                    .background(Color.White.copy(alpha = 0.4f))
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxHeight()
                        .let {
                            when (index) {
                                currentStep -> it.fillMaxWidth(percent.value)
                                in 0..currentStep -> it.fillMaxWidth(1f)
                                else -> it
                            }
                        },
                ) {}
            }
            if (index != steps) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
