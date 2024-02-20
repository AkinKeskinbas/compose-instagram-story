package com.keak.instagramstory

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable

interface SlicedProgressBarInterface {
    @Composable
    fun content(
        steps: Int,
        currentStep: Int,
        paused: Boolean,
        onFinished: () -> Unit,
        beforeDurationEnd: () -> Unit,
        percent: Animatable<Float, AnimationVector1D>
    )
}