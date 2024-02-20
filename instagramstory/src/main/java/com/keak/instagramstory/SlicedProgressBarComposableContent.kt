package com.keak.instagramstory

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable

object SlicedProgressBarComposableContent : SlicedProgressBarInterface {
    @Composable
    override fun content(
        steps: Int,
        currentStep: Int,
        paused: Boolean,
        onFinished: () -> Unit,
        beforeDurationEnd: () -> Unit,
        percent: Animatable<Float, AnimationVector1D>
    ) = InstagramSlicedProgressBar(
        steps =steps,
        currentStep =currentStep,
        paused = paused,
        onFinished = onFinished,
        beforeDurationEnd = beforeDurationEnd,
        percent = percent
    )
}