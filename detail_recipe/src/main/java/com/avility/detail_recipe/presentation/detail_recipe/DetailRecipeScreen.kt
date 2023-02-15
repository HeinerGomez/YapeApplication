package com.avility.detail_recipe.presentation.detail_recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.avility.core.navigation.Screen
import com.avility.core.utils.emptyValue
import  com.avility.detail_recipe.R as Resources
import  com.avility.core.R as CoreResources
import com.avility.detail_recipe.presentation.detail_recipe.components.CarouselBadge
import com.avility.ui_components.components.buttons.StandardButton
import com.avility.ui_components.components.others.GenericError
import com.avility.ui_components.components.others.GenericLoading
import com.avility.ui_components.components.widgets.BadgeTag
import com.avility.ui_components.components.widgets.ScrollableContent
import com.avility.ui_components.components.widgets.TopRibbonActionButton

@Composable
fun DetailRecipeScreen(
    navController: NavController,
    viewModel: DetailRecipeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            TopRibbonActionButton(
                icon = Icons.Filled.ArrowBack,
                iconColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary,
                onTapIcon = {
                    navController.popBackStack()
                }
            )
            
            if (state.recipe != null && state.error.isBlank()) {
                ContentBody(state, navController)
            }
            
            if (state.isLoading) {
                GenericLoading()
            }
            
            if (state.error.isNotBlank()) {
                GenericError(messageError = String.emptyValue())
            }

        }
    }
}

@Composable
private fun ContentBody(state: DetailRecipeState, navController: NavController) {
    ScrollableContent(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(CoreResources.dimen.spacing_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .width(dimensionResource(CoreResources.dimen.measure_150))
                .height(dimensionResource(CoreResources.dimen.measure_150))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = state.recipe!!.image
                    ),
                    contentDescription = String.emptyValue(),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(
                dimensionResource(CoreResources.dimen.spacing_extra_large))
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.recipe!!.title,
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = state.recipe.description,
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Light
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4
                )
                Spacer(modifier = Modifier.height(
                    dimensionResource(CoreResources.dimen.spacing_extra_small))
                )
                BadgeTag(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                dimensionResource(CoreResources.dimen.spacing_extra_small)
                            )
                        )
                        .background(
                            MaterialTheme.colors.primary
                        ),
                    value = state.recipe.country,
                    textStyle = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.background
                    ),
                    modifierText = Modifier.padding(
                        dimensionResource(CoreResources.dimen.spacing_extra_small)
                    )
                )
            }

        }
        Spacer(modifier = Modifier.height(
            dimensionResource(CoreResources.dimen.spacing_extra_large)
        ))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(CoreResources.dimen.spacing_medium)
                )
        ) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(Resources.string.title_ingredients),
                    style = MaterialTheme.typography.h5
                )
                Divider(Modifier.padding(
                    vertical = dimensionResource(CoreResources.dimen.spacing_medium)
                ))
                CarouselBadge(
                    items = state.recipe!!.ingredients
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(CoreResources.dimen.spacing_extra_large),
                    horizontal = dimensionResource(CoreResources.dimen.spacing_medium)
                )
        ) {
            Column {
                Text(
                    text = stringResource(Resources.string.title_instructions),
                    style = MaterialTheme.typography.h5
                )
                Divider(Modifier.padding(
                    vertical = dimensionResource(CoreResources.dimen.spacing_medium)
                ))
                Text(
                    text = state.recipe!!.instructions
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(CoreResources.dimen.spacing_medium)
                )
        ) {
            StandardButton(
                text = stringResource(Resources.string.btn_text_show_map),
                backgroundColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.background,
                onTap = {
                    val location = state.recipe!!.location
                    navController.navigate(Screen.MapRecipeScreen.route + "/${location.lat}/${location.lng}")
                }
            )
        }
    }
}