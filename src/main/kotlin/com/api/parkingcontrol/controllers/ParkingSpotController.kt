package com.api.parkingcontrol.controllers

import com.api.parkingcontrol.dtos.ParkingSpotDto
import com.api.parkingcontrol.models.ParkingSpotModel
import com.api.parkingcontrol.services.ParkingSpotService
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/parking-spot")
class ParkingSpotController(private val parkingSpotService: ParkingSpotService) {


    @PostMapping
    fun saveParkingSpot(@RequestBody @Valid parkingSpotDto: ParkingSpotDto): ResponseEntity<Any> {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.licensePlateCar)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License plate car is already in use")
        }

        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.parkingSpotNumber)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot is already in use")
        }

        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.apartment, parkingSpotDto.block)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Conflict: Parking spot already registered for this apartment/block")
        }

        val parkingSpotModel = ParkingSpotModel()
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel)
        parkingSpotModel.registrationDate = LocalDateTime.now(ZoneId.of("UTC"))
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel))
    }

    @GetMapping
    fun getAllParkingSpots(
        @PageableDefault(
            page = 0, size = 10, sort = ["id"], direction = Sort.Direction.ASC
        ) pageable: Pageable
    ): ResponseEntity<Page<ParkingSpotModel>> {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable))
    }

    @GetMapping("/{id}")
    fun getOneParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<Any> {
        val parkingSpotModelOptional = parkingSpotService.findById(id)

        if (!parkingSpotModelOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found")
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get())
    }

    @DeleteMapping("/{id}")
    fun deleteParkingSpot(@PathVariable(value = "id") id: UUID): ResponseEntity<Any> {
        val parkingSpotModelOptional = parkingSpotService.findById(id)

        if (!parkingSpotModelOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found")
        }

        parkingSpotService.delete(parkingSpotModelOptional.get())

        return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted")
    }

    @PutMapping("/{id}")
    fun updateParkingSpot(
        @PathVariable(value = "id") id: UUID, @RequestBody @Valid parkingSpotDto: ParkingSpotDto
    ): ResponseEntity<Any> {

        val parkingSpotModelOptional = parkingSpotService.findById(id)

        if (!parkingSpotModelOptional.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found")
        }

        val parkingSpotModel = ParkingSpotModel()
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel)

        parkingSpotModel.id = parkingSpotModelOptional.get().id
        parkingSpotModel.registrationDate = parkingSpotModelOptional.get().registrationDate

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel)
    }
}