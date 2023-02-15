package com.avility.ui_components.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import com.avility.ui_components.R as Resources


@Composable
fun StandardButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    onTap: () -> Unit = {},
    icon: Painter? = null
) {
    Button(
        onClick = onTap,
        modifier = modifier
            .fillMaxWidth()
            .height(
                dimensionResource(Resources.dimen.measure_50)
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            icon?.let {
                Image(
                    painter = it,
                    contentDescription = ""
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text,
                    modifier = Modifier.align(Alignment.Center),
                    color = textColor
                )
            }
        }
    }
}