package com.api.parkingcontrol.repositories

import com.api.parkingcontrol.models.ParkingSpotModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ParkingSpotRepository : JpaRepository<ParkingSpotModel, UUID> {

    fun existsByLicensePlateCar(licensePlateCar: String): Boolean;
    fun existsByParkingSpotNumber(parkingSpotNumber: String): Boolean;
    fun existsByApartmentAndBlock(apartment: String, block: String): Boolean;

}