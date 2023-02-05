package com.api.parkingcontrol.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class ParkingSpotDto(

    @NotBlank
    val parkingSpotNumber: String,
    @NotBlank
    @Size(max = 7)
    val licensePlateCar: String,
    @NotBlank
    val brandCar: String,
    @NotBlank
    val modelCar: String,
    @NotBlank
    val colorCar: String,
    @NotBlank
    val responsibleName: String,
    @NotBlank
    val apartment: String,
    @NotBlank
    val block: String,
)