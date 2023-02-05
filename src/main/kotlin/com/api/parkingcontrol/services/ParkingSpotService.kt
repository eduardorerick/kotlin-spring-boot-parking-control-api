package com.api.parkingcontrol.services

import com.api.parkingcontrol.models.ParkingSpotModel
import com.api.parkingcontrol.repositories.ParkingSpotRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ParkingSpotService(private val parkingSpotRepository: ParkingSpotRepository) {


    fun save(parkingSpotModel: ParkingSpotModel): ParkingSpotModel {
        return parkingSpotRepository.save(parkingSpotModel)
    }

    fun existsByLicensePlateCar(licensePlateCar: String): Boolean {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar)
    }

    fun existsByParkingSpotNumber(parkingSpotNumber: String): Boolean {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber)
    }

    fun existsByApartmentAndBlock(apartment: String, block: String): Boolean {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block)
    }

    fun findAll(pageable: Pageable): Page<ParkingSpotModel> {
        return parkingSpotRepository.findAll(pageable)
    }

    fun findById(id: UUID): Optional<ParkingSpotModel> {
        return parkingSpotRepository.findById(id)

    }

    fun delete(parkingSpot: ParkingSpotModel) {
        return parkingSpotRepository.delete(parkingSpot)
    }
}