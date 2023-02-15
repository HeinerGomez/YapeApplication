package com.avility.ui_components.components.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*
import com.avility.ui_components.R as Resources

@Composable
fun GenericLoading() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            Resources.raw.loading
        )
    )
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.5f
    )

    LottieAnimation(
        composition,
        progress,
    )
}