package com.avility.ui_components.components.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun BadgeTag(
    modifier: Modifier,
    value: String,
    textStyle: TextStyle,
    modifierText: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            value,
            style = textStyle,
            overflow = TextOverflow.Ellipsis,
            modifier = modifierText
        )
    }
}