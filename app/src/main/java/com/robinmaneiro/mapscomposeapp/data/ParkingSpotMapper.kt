package com.robinmaneiro.mapscomposeapp.data

import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot


// Mapper in Clean Architecture will convert our ParkingSpotEntity into ParkingSpot.class

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(
        lat = lat,
        lng = lng,
        id = id
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}