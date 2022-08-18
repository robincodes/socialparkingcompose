package com.robinmaneiro.mapscomposeapp.domain

import com.robinmaneiro.mapscomposeapp.data.ParkingSpotDao
import com.robinmaneiro.mapscomposeapp.data.toParkingSpot
import com.robinmaneiro.mapscomposeapp.data.toParkingSpotEntity
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot
import com.robinmaneiro.mapscomposeapp.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepositoryImpl(
    private val dao: ParkingSpotDao
) : ParkingSpotRepository {
    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun getParkingSpot(): Flow<List<ParkingSpot>> {
        return dao.getParkingSpots().map { spots ->
            spots.map { it.toParkingSpot() }
        }
    }
}