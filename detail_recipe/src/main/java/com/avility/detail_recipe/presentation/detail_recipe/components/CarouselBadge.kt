package com.avility.detail_recipe.presentation.detail_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.avility.core.R
import com.avility.detail_recipe.domain.model.Ingredient
import com.avility.ui_components.components.widgets.BadgeTag

@Composable
fun CarouselBadge(
    items: List<Ingredient>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState()
    ) {

        itemsIndexed(items) { _, item ->
            BadgeTag(
                modifier = Modifier
                    .padding(
                        dimensionResource(R.dimen.spacing_min)
                    )
                    .clip(RoundedCornerShape(
                        dimensionResource(R.dimen.spacing_small)
                    ))
                    .background(
                        MaterialTheme.colors.secondary
                    ),
                value = item.name,
                textStyle = MaterialTheme.typography.button.copy(
                    color = MaterialTheme.colors.background
                ),
                modifierText = Modifier.padding(
                    dimensionResource(R.dimen.spacing_small)
                )
            )
            Spacer(modifier = Modifier.width(
                dimensionResource(R.dimen.spacing_small)
            ))
        }

    }
}