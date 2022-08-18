package com.robinmaneiro.mapscomposeapp.domain.repository

import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {

    suspend fun insertParkingSpot(spot: ParkingSpot) //Use the Domain layer class, not the Entity one

    suspend fun deleteParkingSpot(spot: ParkingSpot)

    suspend fun getParkingSpot(): Flow<List<ParkingSpot>>
}