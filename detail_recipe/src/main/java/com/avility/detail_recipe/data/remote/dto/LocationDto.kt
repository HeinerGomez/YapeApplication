package com.avility.detail_recipe.data.remote.dto


import com.avility.detail_recipe.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)

fun LocationDto.toLocation() = Location(
    lat, lng
)