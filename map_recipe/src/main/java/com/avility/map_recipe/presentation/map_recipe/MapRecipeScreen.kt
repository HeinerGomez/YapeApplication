package com.avility.map_recipe.presentation.map_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.avility.ui_components.components.widgets.TopRibbonActionButton
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.avility.map_recipe.R as Resources

@Composable
fun MapRecipeScreen(
    navController: NavController,
    lat: Double?,
    lng: Double?
) {

    var countryLocation: LatLng? = null
    var cameraPositionState: CameraPositionState? = null

    if (lat != null && lng != null) {
        countryLocation = LatLng(lat, lng)
        cameraPositionState = rememberCameraPositionState{
            position = CameraPosition.fromLatLngZoom(countryLocation, 5f)
        }
    }

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
            if (cameraPositionState != null && countryLocation != null) {
                GoogleMap(
                    Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = countryLocation),
                        title = stringResource(Resources.string.yape_point),
                        snippet = stringResource(Resources.string.yape_marker),
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
                    )
                }
            } else {
                // here show error widget
            }
        }

    }

}