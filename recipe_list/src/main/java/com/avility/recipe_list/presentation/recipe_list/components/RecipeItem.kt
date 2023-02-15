package com.avility.recipe_list.presentation.recipe_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.avility.core.R
import com.avility.core.utils.emptyValue
import com.avility.recipe_list.domain.model.RecipeItem
import com.avility.ui_components.components.widgets.BadgeTag

@Composable
fun RecipeItem(
    recipeItem: RecipeItem,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .padding(
                dimensionResource(R.dimen.spacing_small)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(
                    dimensionResource(R.dimen.measure_100)
                )
                .height(
                    dimensionResource(R.dimen.measure_100)
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = recipeItem.image
                ),
                contentDescription = String.emptyValue(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(
            dimensionResource(R.dimen.spacing_extra_large)
        ))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = recipeItem.title,
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = recipeItem.description,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Light
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(R.dimen.spacing_extra_small)
            ))
            BadgeTag(
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        dimensionResource(R.dimen.spacing_extra_small)
                    ))
                    .background(
                        MaterialTheme.colors.primary
                    ),
                value = recipeItem.country,
                textStyle = MaterialTheme.typography.overline.copy(
                    color = MaterialTheme.colors.background
                ),
                modifierText = Modifier.padding(
                    dimensionResource(R.dimen.spacing_extra_small)
                )
            )
        }
    }
}