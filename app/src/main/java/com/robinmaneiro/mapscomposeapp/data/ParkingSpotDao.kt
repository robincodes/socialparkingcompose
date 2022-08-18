package com.robinmaneiro.mapscomposeapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //If we try to insert a new parking spot here that already exists (same id) it will just override it
    suspend fun insertParkingSpot(spot: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpotEntity)

    @Query("SELECT * FROM parkingspotentity") // Select everything from the ParkingSpotEntity table
    //The query function returns a flow, and flow is already asynchronous
    fun getParkingSpots(): Flow<List<ParkingSpotEntity>>
}