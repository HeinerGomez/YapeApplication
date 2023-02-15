package com.avility.ui_components.components.widgets

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.avility.ui_components.R as Resources

@Composable
fun TopRibbonActionButton(
    icon: ImageVector,
    iconColor: Color,
    backgroundColor: Color,
    onTapIcon: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                dimensionResource(Resources.dimen.measure_50)
            )
            .background(backgroundColor)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onTapIcon) {
               Icon(
                   imageVector = icon,
                   contentDescription = "",
                   tint = iconColor
               )
            }
        }
    }
}
