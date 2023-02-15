package com.avility.recipe_list.presentation.recipe_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.core.R
import com.avility.core.navigation.Screen
import com.avility.core.utils.emptyValue
import com.avility.ui_components.components.fields.TextFieldSearch
import com.avility.recipe_list.presentation.recipe_list.components.RecipeItem
import com.avility.ui_components.components.others.GenericEmptyScreen
import com.avility.ui_components.components.others.GenericError
import com.avility.ui_components.components.others.GenericLoading
import com.avility.recipe_list.R as Resources

@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(R.dimen.measure_80)
                    )
                    .background(
                        MaterialTheme.colors.primary
                    ),
                contentAlignment = Alignment.Center
            ) {
                TextFieldSearch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            dimensionResource(R.dimen.measure_50)
                        )
                        .padding(
                            start = dimensionResource(R.dimen.spacing_medium),
                            end = dimensionResource(R.dimen.spacing_medium)
                        ),
                    text = viewModel.searchQuery.value,
                    hint = stringResource(Resources.string.placeholder_search_field),
                    onTextChange = viewModel::searchRecipes
                )
            }

            Spacer(modifier = Modifier.height(
                dimensionResource(R.dimen.spacing_small)
            ))

            LazyColumn {
                items(state.recipes) { recipe ->
                    RecipeItem(
                        recipeItem = recipe,
                        onItemClick = {
                            navController.navigate(Screen.DetailRecipeScreen.route)
                        }
                    )
                    Divider()
                }
            }

            if (state.recipes.isEmpty() && state.error.isBlank() && !state.isLoading) {
                GenericEmptyScreen(
                    stringResource(Resources.string.disclaimer_empty_screen),
                    MaterialTheme.colors.primaryVariant
                )
            }

            if(state.isLoading) {
                GenericLoading()
            }

            if (state.error.isNotBlank()) {
                GenericError(String.emptyValue())
            }

        }
    }
}